package io.pandora.mall.generate;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.internal.DefaultCommentGenerator;
import org.mybatis.generator.internal.util.StringUtility;

import java.util.Properties;

/**
 * 自定义注释生成器
 * 该生成器会生成 Get/Set 方法,实现序列化
 * @see com.baomidou.mybatisplus.annotation.TableName 会自动加上MybatisPlus表名注解以便支持CRUD操作
 *
 * @author Created by macro update by Mr_zhou by 2020-12-13
 */
public class CommentGenerator extends DefaultCommentGenerator {

    private boolean addRemarkComments = true;

    private static final String MAPPER_SUFFIX="Mapper";
    private static final String EXAMPLE_SUFFIX="Example";
    // @TableName 注解所在包
    private static final String TABLE_NAME="com.baomidou.mybatisplus.annotation.TableName";
    private static final String API_MODEL_PROPERTY_FULL_CLASS_NAME="io.swagger.annotations.ApiModelProperty";

    /**
     * 设置用户配置的参数
     */
    @Override
    public void addConfigurationProperties(Properties properties) {
        super.addConfigurationProperties(properties);
        this.addRemarkComments = StringUtility.isTrue(properties.getProperty("addRemarkComments"));
    }

    /**
     * 给字段添加注释
     */
    @Override
    public void addFieldComment(Field field, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
        String remarks = introspectedColumn.getRemarks();

        //根据参数和备注信息判断是否添加备注信息
        if(addRemarkComments && StringUtility.stringHasValue(remarks)){
            if(remarks.contains("\"")) remarks = remarks.replace("\"","'");

            //给model的字段添加swagger注解
            field.addJavaDocLine("@ApiModelProperty(value = \""+ remarks +"\")");
        }
    }

    /**
     * 给model的字段添加注释
     */
    private void addFieldJavaDoc(Field field, String remarks) {
        field.addJavaDocLine("/**");
        //获取数据库字段的备注信息
        String[] remarkLines = remarks.split(System.getProperty("line.separator"));
        for(String remarkLine : remarkLines){
            field.addJavaDocLine(" * "+ remarkLine);
        }
        addJavadocTag(field, false);
        field.addJavaDocLine(" */");
    }

    /**
     * 重新 Java文件注释方法 导入生成注解所需依赖包
     * @param compilationUnit
     */
    @Override
    public void addJavaFileComment(CompilationUnit compilationUnit) {
        super.addJavaFileComment(compilationUnit);
        //只在model中添加swagger注解类的导入
        if(!compilationUnit.getType().getFullyQualifiedName().contains(MAPPER_SUFFIX)&&!compilationUnit.getType().getFullyQualifiedName().contains(EXAMPLE_SUFFIX)){
            compilationUnit.addImportedType(new FullyQualifiedJavaType(API_MODEL_PROPERTY_FULL_CLASS_NAME));
            compilationUnit.addImportedType(new FullyQualifiedJavaType(TABLE_NAME));
        }
    }

    /**
     * 重写类注释方法 增加 TableName 注解
     *
     * @param topLevelClass
     * @param introspectedTable
     */
    @Override
    public void addModelClassComment(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        String tableName = introspectedTable.getFullyQualifiedTableNameAtRuntime();
        topLevelClass.addAnnotation("@TableName(\"" + tableName + "\")");
    }
}
