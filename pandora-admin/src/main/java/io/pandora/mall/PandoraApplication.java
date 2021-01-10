package io.pandora.mall;

import io.pandora.mall.util.spring.SpringContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.context.annotation.PropertySource;

/**
 *                      .-"``"-.
 *                     /______; \
 *                    {_______}\|
 *                    (/ a a \)(_)
 *                    (.-.).-.)
 *       _______ooo__(    ^    )____________
 *      /             '-.___.-'             \
 *     |            Pandora stark            |
 *     |                                     |
 *     |         Copyright 2019-2020         |
 *     |      Created by zhou on 2019/6/29   |
 *      \___________________________________/
 *                    |_  |  _|  jgs
 *                    \___|___/
 *                    {___|___}
 *                     |_ | _|
 *                     /-'Y'-\
 *                    (__/ \__)
 */
@SpringBootApplication
@PropertySource(value = "config/config.properties",encoding = "UTF-8")
public class PandoraApplication {

    private static Logger LOGGER = LoggerFactory.getLogger(PandoraApplication.class);

    /**
     * 程序入口
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(PandoraApplication.class,args);
        printProjectConfigs();
    }

    /**
     * 输出项目配置信息
     */
    private static void printProjectConfigs() {
        ServerProperties serverProperties = SpringContextHolder
                .getApplicationContext()
                .getBean(ServerProperties.class);
        LOGGER.info("run at http://127.0.0.1:{}/doc.html", serverProperties.getPort());
    }
}
