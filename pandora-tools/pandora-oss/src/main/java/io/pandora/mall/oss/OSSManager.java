package io.pandora.mall.oss;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Created by mr_zhou on 2020/12/30
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OSSManager implements UploadServer {

    private UploadServer uploadServer;

    @Override
    public String upload(byte[] uploadBytes, String fileName) {
        return uploadServer.upload(uploadBytes,fileName);
    }
}
