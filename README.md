<h1 style="text-align: center">Pandora-潘多拉魔盒</h1>

#### 项目简介
    s基于当前流行技术组合的前后端分离商城系统
    SpringBoot2 + MybatisPlus + SpringSecurity + JWT + Redis + ElasticSearch + RabbitMQ + Vue的前后端分离的商城系统; 
    包含：商城、商品搜索、客服系统、订单服务、物流服务、用户足迹、用户收藏、店铺关注、购物车、优惠卷抵扣、积分兑换、多店铺等功能；

#### 体验地址(暂未开放)
|     |   演示地址  |    https://doc.pandla.io  |
|---  |--- | --- |

### 1.0 版本功能预告：
    1、商城首页：轮播图广告、商品热卖榜、每日上新榜、商品推荐榜；
    2、商品服务：商品搜索、商品分类、商品添加、商品浏览记录、商品评价、商品属性；
    3、用户服务：用户收货地址、用户浏览记录、用户商品收藏、用户优惠卷、用户购物车；
    4、订单服务：创建订单、订单物流查询、订单支付、订单延时取消；
    5、客服系统：智能客服、人工客服、自助查询；
    6、店铺入驻：店铺审核、开通店铺、店铺推荐；
    7、优惠卷中心、积分商城等功能；

#### 项目源码
|     |  API系统源码 |   前端源码  |
|---  |--- | --- |
|   码云  |  https://gitee.com/zhouhouxing/pandora-mall  | https://gitee.com/zhouhouxing/pandora-web |


## 商城功能
* 一：商品模块：商品添加、规格设置，商品上下架等
* 二：订单模块：下单、购物车、支付，发货、收货、评价、退款等
* 三：营销模块：积分、优惠券、分销、砍价、拼团、秒杀、多门店等
* 四：微信模块：自定义菜单、自动回复、微信授权、图文管理、模板消息推送(待开放)
* 五：配置模块：系统配置、第三方密钥对配置、权限配置等
* 六：用户模块：登陆、注册、会员卡、充值等

## 社交功能
* 一：用户模块：用户注册、用户登录、完善信息、修改信息
* 二：定位模块：附近的人、地图找人、附近筛选等
* 三：动态模块：同城动态、推荐动态、话题动态、动态点赞、动态评论回复等
* 四：通讯聊天：用户黑名单、发送信息(图片、文字、表情)、撤回消息、屏蔽用户
* 五：粉丝关注：关注好友、取消关注、好友备注、邀请用户
* 六：系统通知：系统公告、举报提醒、点赞提醒、私信提醒、评论回复提醒

#### 项目结构
项目采用分模块开发方式
- pandora-admin         - **后台模块**
- pandora-biz           - **第三方组件**
- pandora-common        - **公共模块**
- pandora-generate      - **代码生成模块**
- pandora-job           - **定时任务**
- pandora-logging       - **日志模块**
- pandora-mbp           - **mybatisPlus**
- pandora-push          - **通用推送模块**
- pandora-shop          - **商城模块**


## 技术选型
* 1 后端使用技术
    * 1.1 SpringBoot2
    * 1.2 mybatis、MyBatis-Plus
    * 1.3 SpringSecurity
    * 1.5 Druid
    * 1.6 Slf4j
    * 1.7 FastJson
    * 1.8 JWT
    * 1.9 Redis
    * 1.10 Quartz
    * 1.11 Mysql
    * 1.12 Swagger-BootStrap
    * 1.14 Lombok
    * 1.15 Hutool
    * 1.16 Mapstruct
	* 1.17 Jedis
	* 1.18 RabbitMQ
        
* 前端使用技术
    * 2.1 Vue 全家桶
    * 2.2 Element
    * 2.3 mpvue
    * 2.3 uniapp

