<h1 style="text-align: center">Pandora-商城API</h1>

#### 项目简介
Pandora：
    基于当前流行技术组合的前后端分离商城系统
    SpringBoot2+MybatisPlus+SpringSecurity+jwt+redis+Vue的前后端分离的商城系统， 
    包含商城、拼团、砍价、商户管理、 秒杀、优惠券、积分、会员、充值、多门店等功能；

### 1.0 版本功能预告：
    1、商品列表、商品收藏、商城客服
    2、购物车、结算功能
    3、用户收货地址
    4、用户下单、商品售后
    5、我的浏览足迹

#### 项目源码
|     |  API系统源码 |   前端源码  |
|---  |--- | --- |
|   码云  |  https://gitee.com/zhouhouxing/pandora-mall  | https://gitee.com/zhouhouxing/pandora-web |


## 商城功能
* 一：商品模块：商品添加、规格设置，商品上下架等
* 二：订单模块：下单、购物车、支付，发货、收货、评价、退款等
* 三：营销模块：积分、优惠券、分销、砍价、拼团、秒杀、多门店等
* 四：微信模块：自定义菜单、自动回复、微信授权、图文管理、模板消息推送
* 五：配置模块：各种配置
* 六：用户模块：登陆、注册、会员卡、充值等
* 七：其他等

#### 项目结构
项目采用分模块开发方式
- pandora-common    公共模块
- pandora-admin     后台模块
- pandora-logging   日志模块
- pandora-tools     第三方工具模块
- pandora-generator 代码生成模块
- pandora-shop      商城模块
- pandora-mproot    mybatisPlus


## 技术选型
* 1 后端使用技术
    * 1.1 SpringBoot2
    * 1.2 mybatis、MyBatis-Plus
    * 1.3 SpringSecurity
    * 1.5 Druid
    * 1.6 Slf4j
    * 1.7 Fastjson
    * 1.8 JWT
    * 1.9 Redis
    * 1.10 Quartz
    * 1.11 Mysql
    * 1.12 swagger
    * 1.13 WxJava
    * 1.14 Lombok
    * 1.15 Hutool
    * 1.16 Mapstruct
	* 1.17 Redisson
	* 1.18 RabbitMQ
        
* 前端使用技术
    * 2.1 Vue 全家桶
    * 2.2 Element
    * 2.3 mpvue
    * 2.3 uniapp

