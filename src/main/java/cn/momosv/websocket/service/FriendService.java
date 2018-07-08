package cn.momosv.websocket.service;

import cn.momosv.websocket.model.TbFriendPO;

public interface FriendService extends BasicService{
    TbFriendPO selectByPrimaryKey1(String id);
}
