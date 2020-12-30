package io.pandora.mall.oss.local;

import io.pandora.mall.oss.UploadServer;
import io.pandora.mall.util.DateUtils;
import io.pandora.mall.util.StringUtils;
import io.pandora.mall.util.http.HttpContextUtils;
import lombok.Setter;
import java.io.*;
import java.util.Date;

/**
 * @author Created by mr_zhou on 2020/12/30
 */
public class LocalUploadServer implements UploadServer {

    @Setter
    private LocalUploadProperties properties;

    public LocalUploadServer(LocalUploadProperties properties) {
        this.properties = properties;
    }

    @Override
    public String upload(byte[] uploadBytes, String fileName) {
        String localDir = getLocalDir();
        String userDir = getUserDir(fileName);
        String dir = localDir + userDir;
        // 校验是否存在 不存在则创建
        ensureDirExit(dir);
        String filePath = dir + File.separator + fileName;
        bytesToFile(uploadBytes,filePath);
        return properties.getRootURL() + "/" + userDir + File.separator + fileName;
    }

    private String getLocalDir(){
        String localPath = properties.getLocalPath();
        if (StringUtils.isBlank(localPath)){
            localPath = HttpContextUtils.getServletRequest().getServletContext().getRealPath("/") + "upload";
        }
        boolean endWith = !localPath.endsWith(File.separator);
        if (endWith) {
            localPath += File.separator;
        }
        return localPath;
    }

    private String getUserDir(String fileName){
        String format = DateUtils.format(new Date(), DateUtils.DATE_TIME_PATTERN_8);
        int hashCode = fileName.hashCode();
        String dir1 = Integer.toHexString(hashCode & 0XF);
        String dir2 = Integer.toHexString(hashCode >> 4 & 0XF);

        String path = format + File.separator + dir1 + File.separator + dir2;
        return path;
    }

    private void ensureDirExit(String path){
        File file = new File(path);
        if (!file.exists()){
            file.mkdirs();
        }
    }

    private File bytesToFile(byte[] bytes,String filePath){
        File file = new File(filePath);
        OutputStream out = null;
        BufferedOutputStream bos = null;

        try {
            out = new FileOutputStream(file);
            bos = new BufferedOutputStream(out);
            bos.write(bytes);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if (null != bos){
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != out){
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return file;
    }


}
