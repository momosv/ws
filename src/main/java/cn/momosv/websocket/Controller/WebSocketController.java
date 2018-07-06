package cn.momosv.websocket.Controller;


import cn.momosv.websocket.entity.ServerMessage;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.simp.user.SimpUser;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.Map;


/**
 *
 * @ClassName: WebSocketAction
 * @Description: websocket控制层
 * @author cheng
 * @date 2017年9月27日 下午4:20:58
 */
@Controller
public class WebSocketController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    //spring提供的发送消息模板
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private SimpUserRegistry userRegistry;



    @MessageMapping("/toAll")
    @SendTo("/queue/toAll")
    public ServerMessage onLine(String name) {
      String m= (String) JSON.parseObject(name).get("name");

        //发送消息给指定用户
      //  messagingTemplate.convertAndSend( "/queue/toAll", new ServerMessage("上线了"));
        return new ServerMessage(m+" 上线了");
    }

    @RequestMapping(value = "/templateTest")
    public void templateTest() {
        logger.info("当前在线人数:" + userRegistry.getUserCount());
        int i = 1;
        for (SimpUser user : userRegistry.getUsers()) {
            logger.info("用户" + i++ + "---" + user);
        }
        //发送消息给指定用户
        messagingTemplate.convertAndSendToUser("test", "/queue/message", new ServerMessage("服务器主动推的数据"));
    }
    @RequestMapping(value = "/toUser")
    public void toUser(String toUser,String msg) {
        logger.info("当前在线人数:" + userRegistry.getUserCount());
        int i = 1;
        for (SimpUser user : userRegistry.getUsers()) {
            logger.info("用户" + i++ + "---" + user);
        }
        //发送消息给指定用户
        messagingTemplate.convertAndSendToUser(toUser, "/queue/message", new ServerMessage("我"+msg));
    }
    @RequestMapping(value = "/toAll")
    public void toAll(@RequestParam(defaultValue = "这是系统默认消息") String msg) {
        logger.info("当前在线人数:" + userRegistry.getUserCount());
        int i = 1;
        for (SimpUser user : userRegistry.getUsers()) {
            logger.info("用户" + i++ + "---" + user);
        }
        //发送消息给指定用户
        messagingTemplate.convertAndSend("/queue/toAll", new ServerMessage("all:"+msg));
    }

    @MessageMapping("/toChat")
    public void chat(String data) {
        String toUser= (String) JSON.parseObject(data).get("toUser");
        String msg= (String) JSON.parseObject(data).get("msg");
        logger.info("当前在线人数:" + userRegistry.getUserCount());
        int i = 1;
        for (SimpUser user : userRegistry.getUsers()) {
            logger.info("用户" + i++ + "---" + user);
        }
        //发送消息给指定用户
        messagingTemplate.convertAndSendToUser(toUser, "/queue/toChat", new ServerMessage(msg));
    }

    @MessageMapping("/queue/toChat")
    public ServerMessage toChat(String name) {
        String m= (String) JSON.parseObject(name).get("name");

        //发送消息给指定用户
        //  messagingTemplate.convertAndSend( "/queue/toAll", new ServerMessage("上线了"));
        return new ServerMessage(m+" 上线了");
    }


}