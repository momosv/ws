package cn.momosv.websocket;

import cn.momosv.websocket.model.TbFriendMessagePO;
import cn.momosv.websocket.model.TbFriendPO;
import cn.momosv.websocket.model.TbUserPO;
import cn.momosv.websocket.push.ServerPush;
import cn.momosv.websocket.service.FriendMessageService;
import cn.momosv.websocket.service.FriendService;
import cn.momosv.websocket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.Map;
import java.util.UUID;


@EnableScheduling//定时任务
@EnableAsync // 开启异步任务支持

@ConfigurationProperties("application.yml") //接收application.yml中的myProps下面的属性
@Controller
@SpringBootApplication
public class WebsocketApplication extends SpringBootServletInitializer {



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

    @Autowired
    private UserService userService;


    @ResponseBody
    @RequestMapping("addUser")
    public String addUser(){
        TbUserPO userPO = new TbUserPO();
        userPO.setId(UUID.randomUUID().toString());
        userPO.setUserName("momo");
        userService.insertOne(userPO);
        TbUserPO userPO1= userService.selectByPrimaryKey1(userPO.getId());
        return userPO1.toString();
    }
    @Autowired
    private FriendService friendService;

    @ResponseBody
    @RequestMapping("addFri")
    public String addFri(){
        TbFriendPO friendPO = new TbFriendPO();
        friendPO.setId(UUID.randomUUID().toString());
        friendPO.setCreateTime(new Date());
        friendService.insertOne(friendPO);
        TbFriendPO friendPO1= friendService.selectByPrimaryKey1(friendPO.getId());
        return friendPO1.toString();
    }

    @Autowired
    private FriendMessageService friendMessageService;

    @ResponseBody
    @RequestMapping("addM")
    public String addM(){
        TbFriendMessagePO friendMessagePO = new TbFriendMessagePO();
        friendMessagePO.setId(UUID.randomUUID().toString());
        friendMessagePO.setContent("momo");
        friendMessageService.insertOne(friendMessagePO);
        return friendMessagePO.toString();
    }


}
