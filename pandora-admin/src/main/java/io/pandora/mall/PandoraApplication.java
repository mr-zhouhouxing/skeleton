package io.pandora.mall;

import io.pandora.mall.util.spring.SpringContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.context.annotation.PropertySource;

/**
 * @author Created by mr_zhou on 2020/11/26
 */
@SpringBootApplication
@PropertySource(value = "config/config.properties",encoding = "UTF-8")
public class PandoraApplication {

    private static Logger LOGGER = LoggerFactory.getLogger(PandoraApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(PandoraApplication.class,args);
        printProjectConfigs();
    }

    private static void printProjectConfigs() {
        ServerProperties serverProperties = SpringContextHolder
                .getApplicationContext()
                .getBean(ServerProperties.class);
        LOGGER.info("=============> run at http://localhost:{}/doc.html  <=============", serverProperties.getPort());
    }
}
