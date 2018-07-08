package cn.momosv.websocket.service;

import cn.momosv.websocket.model.TbUserPO;

public interface UserService extends BasicService{
    TbUserPO selectByPrimaryKey1(String id);
}
