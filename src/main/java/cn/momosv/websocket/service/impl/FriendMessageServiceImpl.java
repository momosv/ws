package cn.momosv.websocket.service.impl;

import cn.momosv.websocket.dao1.TbFriendMessagePOMapper;
import cn.momosv.websocket.service.FriendMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("friendMessageService")
public class FriendMessageServiceImpl extends BasicServiceImpl implements FriendMessageService {

    @Autowired
    private TbFriendMessagePOMapper mapper;

    @Resource
    public void setFMMapper(TbFriendMessagePOMapper mapper) {
       setMapper(mapper);
    }

}
