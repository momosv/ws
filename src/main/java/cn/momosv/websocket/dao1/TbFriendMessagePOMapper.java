package cn.momosv.websocket.dao1;

import cn.momosv.websocket.dao.BasicMapper;
import cn.momosv.websocket.model.TbFriendMessagePO;
import org.springframework.stereotype.Repository;


public interface TbFriendMessagePOMapper extends BasicMapper {
    TbFriendMessagePO selectByPrimaryKey(String id);
}