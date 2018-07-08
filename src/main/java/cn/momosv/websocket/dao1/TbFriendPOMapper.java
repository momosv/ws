package cn.momosv.websocket.dao1;

import cn.momosv.websocket.dao.BasicMapper;
import cn.momosv.websocket.model.TbFriendPO;


public interface TbFriendPOMapper extends BasicMapper {
    TbFriendPO selectByPrimaryKey(String id);
}