package io.pandora.mall.module.message;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Created by mr_zhou on 2021/1/12
 */
@RestController
@Api(tags = {"聊天系统 - 【黑名单】"})
@RequestMapping("/${message}/blackList")
public class MessageBlackListController {
}
