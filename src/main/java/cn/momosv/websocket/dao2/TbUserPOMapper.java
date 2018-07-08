package cn.momosv.websocket.dao2;

import cn.momosv.websocket.dao.BasicMapper;
import cn.momosv.websocket.model.TbUserPO;
import org.springframework.stereotype.Repository;


public interface TbUserPOMapper extends BasicMapper {
    TbUserPO selectByPrimaryKey(String id);
}