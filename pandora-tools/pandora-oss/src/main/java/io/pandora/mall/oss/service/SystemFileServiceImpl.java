package io.pandora.mall.oss.service;

import io.pandora.mall.base.service.impl.BaseServiceImpl;
import io.pandora.mall.constant.Constant;
import io.pandora.mall.domian.system.FileDO;
import io.pandora.mall.mapper.system.FileMapper;
import io.pandora.mall.oss.OSSManager;
import io.pandora.mall.util.file.FileType;
import io.pandora.mall.oss.UploadServer;
import io.pandora.mall.oss.config.PandoraProperties;
import io.pandora.mall.oss.util.FileNameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author Created by mr_zhou on 2020/12/30
 */
@Service
public class SystemFileServiceImpl extends BaseServiceImpl<FileMapper, FileDO> implements SystemFileService {

    @Autowired
    private UploadServer uploadServer;
    @Autowired
    private PandoraProperties properties;

    @Override
    public String upload(byte[] uploadBytes, String fileName, int type) {
        String name = buildFileName(type, fileName);
        String url = uploadServer.upload(uploadBytes, name);

        FileDO sysFile = new FileDO(FileType.fileType(fileName), url, new Date());
        this.save(sysFile);

        return url;
    }

    /**
     * 根据 文件来源构建出 文件存储目录+文件名
     *
     * @param source    来源
     * @param fileName  图片名
     * @return
     */
    private String buildFileName(int source , String fileName){
        String name = FileNameUtils.getFileName(fileName, properties);
        if (source == Constant.ZERO)  return name;

        switch (source) {
            case Constant.File.USER_INFO_IMAGE :
                name = Constant.File.USER_INFO_VALUE + name;
                break;
            case Constant.File.USER_CHAT_MESSAGE :
                name = Constant.File.USER_CHAT_MESSAGE_VALUE + name;
                break;
            case Constant.File.USER_DYNAMIC :
                name = Constant.File.USER_DYNAMIC_VALUE + name;
                break;
        }
        return name;
    }

}
