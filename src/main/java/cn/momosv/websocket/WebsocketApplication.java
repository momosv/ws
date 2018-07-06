package cn.momosv.websocket;

import cn.momosv.websocket.push.ServerPush;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;


@EnableScheduling//定时任务
@EnableAsync // 开启异步任务支持
@MapperScan("cn.momosv.websocket.dao")
@ConfigurationProperties("classpath:application.yml") //接收application.yml中的myProps下面的属性
@Controller
@SpringBootApplication
public class WebsocketApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebsocketApplication.class, args);
    }
    @Autowired
    ServerPush serverPush ;
    @RequestMapping("/")
    public String index(@RequestParam(defaultValue = "admin") String name, Map map){
        map.put("name",name);
        return "ws";
    }

    @RequestMapping("chat")
    public String chat(@RequestParam(defaultValue = "admin") String name, Map map){
        map.put("name",name);
        return "chat";
    }
    @RequestMapping("/p")
    public void p(){
        serverPush.templateTest();
    }
}
