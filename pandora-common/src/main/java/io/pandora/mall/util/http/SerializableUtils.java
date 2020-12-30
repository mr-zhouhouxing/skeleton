package io.pandora.mall.util.http;

import io.pandora.mall.exception.CustomException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * Serializable工具(JDK)(也可以使用Protobuf自行百度)
 */
public class SerializableUtils {

    /**
     * logger
     */
    private static final Logger logger = LoggerFactory.getLogger(SerializableUtils.class);

    /**
     * 序列化
     *
     * @param object
     * @return byte[]
     */
    public static byte[] serializable(Object object) {
        ByteArrayOutputStream baos = null;
        ObjectOutputStream oos = null;
        try {
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            return baos.toByteArray();
        } catch (IOException e) {
            logger.error("SerializableUtil工具类序列化出现IOException异常:" + e.getMessage());
            throw new CustomException("SerializableUtil工具类序列化出现IOException异常:" + e.getMessage());
        } finally {
            try {
                if (baos != null) {
                    baos.close();
                }
                if (oos != null) {
                    oos.close();
                }
            } catch (IOException e) {
                logger.error("SerializableUtil工具类反序列化出现IOException异常:" + e.getMessage());
                throw new CustomException("SerializableUtil工具类反序列化出现IOException异常:" + e.getMessage());
            }
        }
    }

    /**
     * 反序列化
     *
     * @param bytes
     * @return java.lang.Object
     */
    public static Object unserializable(byte[] bytes) {
        ByteArrayInputStream bais = null;
        ObjectInputStream ois = null;
        try {
            bais = new ByteArrayInputStream(bytes);
            ois = new ObjectInputStream(bais);
            return ois.readObject();
        } catch (ClassNotFoundException e) {
            logger.error("SerializableUtil工具类反序列化出现ClassNotFoundException异常:" + e.getMessage());
            throw new CustomException("SerializableUtil工具类反序列化出现ClassNotFoundException异常:" + e.getMessage());
        } catch (IOException e) {
            logger.error("SerializableUtil工具类反序列化出现IOException异常:" + e.getMessage());
            throw new CustomException("SerializableUtil工具类反序列化出现IOException异常:" + e.getMessage());
        } finally {
            try {
                if (ois != null) {
                    ois.close();
                }
                if (bais != null) {
                    bais.close();
                }
            } catch (IOException e) {
                logger.error("SerializableUtil工具类反序列化出现IOException异常:" + e.getMessage());
                throw new CustomException("SerializableUtil工具类反序列化出现IOException异常:" + e.getMessage());
            }
        }
    }

}
