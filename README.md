# 虾米打卡后台项目  
前台为微信小程序，项目地址：https://github.com/lizy928/miniprogram-life

### 采用的技术架构： SpringCloud for Alibaba

## 模块介绍：
    life-base     基础服务，数据库操作等
    life-base-api 服务调用API
    life-punch    打卡前台接口服务
    life-user     用户服务
    life-mgt      管理平台（未开发
    life-gateway  网关服务
    life-admin    服务监控
    
## 如何启动呢
  1 先将微信小程序项目利用git工具下载到本地：```git clnoe https://github.com/lizy928/miniprogram-life```<br>
  2 在[微信公众号平台](map.weixin.qq.com)注册一个小程序账号获取appId和appSecret,并下载微信开发者工具 <br>
  3 使用微信开发者工具打开前段项目，按照提示填入appid即可 <br>
    到此前段项目已准备完毕，接下来我们来启动后台项目<br>
  4 克隆后台项目 ``` git clone https://github.com/lizy928/life``` <br>
  5 下载nacos https://github.com/alibaba/nacos，下载后解压，在bin目录下使用命令启动：```sh startup.sh -m standalone``` <br>
  6 启动后将每个项目下的application.yml文件内容复制，并在nacos的配置列表中添加文件,以base为例，文件名：life-basē-dev.yaml <br>
  7 配置文件添加完成后还需要自己安装一个redis <br>
  将各服务启动后即可访问前端项目 <br>
   
