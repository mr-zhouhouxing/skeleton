package io.pandora.mall.search.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

/**
 * @author Created by mr_zhou on 2020/12/31
 */
@Data
@Document(indexName = "test_doc",type = "testBean") //通过这个注解可以声明一个文档，指定其所在的索引库和type
public class TestBean implements Serializable {

    // 必须指定一个id，
    @Id
    private long id;
    // 这里配置了分词器，字段类型，可以不配置，默认也可
    @Field(analyzer = "ik_smart", type = FieldType.Text)
    private String name;
    private Integer age;
    @Field(analyzer = "ik_smart", type = FieldType.Text)
    private String sex;
    @Field(analyzer = "ik_smart", type = FieldType.Text)
    private String desc;

    public TestBean() {
    }

    public TestBean(long id, String name, Integer age, String sex, String desc) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.desc = desc;
    }
}
