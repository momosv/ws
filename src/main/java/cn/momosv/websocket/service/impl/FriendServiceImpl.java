package cn.momosv.websocket.service.impl;

import cn.momosv.websocket.dao1.TbFriendPOMapper;
import cn.momosv.websocket.model.TbFriendPO;
import cn.momosv.websocket.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("friendService")
public class FriendServiceImpl extends BasicServiceImpl  implements FriendService {
    @Autowired
    private TbFriendPOMapper mapper;

    @Resource
    public void setFMapper(TbFriendPOMapper mapper) {
        setMapper(mapper);
    }

    @Override
    public TbFriendPO selectByPrimaryKey1(String id) {
        return mapper.selectByPrimaryKey(id);
    }
}
