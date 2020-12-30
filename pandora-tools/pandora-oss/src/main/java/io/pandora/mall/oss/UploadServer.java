package io.pandora.mall.oss;

/**
 * <pre>
 * 上传服务
 * </pre>
 */
public interface UploadServer {

    String upload(byte[] uploadBytes, String fileName);

}
