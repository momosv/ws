package cn.momosv.websocket.dao;

import cn.momosv.websocket.model.TbUserPO;

public interface TbUserPOMapper {
    TbUserPO selectByPrimaryKey(String id);
}