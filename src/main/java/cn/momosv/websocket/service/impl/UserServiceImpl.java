package cn.momosv.websocket.service.impl;

import cn.momosv.websocket.dao2.TbUserPOMapper;
import cn.momosv.websocket.model.TbUserPO;
import cn.momosv.websocket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("userService")
public class UserServiceImpl extends BasicServiceImpl  implements UserService {

    @Autowired
    private TbUserPOMapper userMapper;

    @Resource
    public void setUserMapper(TbUserPOMapper userMapper) {
        setMapper(userMapper);
    }

    @Override
    public TbUserPO selectByPrimaryKey1(String id) {
        return userMapper.selectByPrimaryKey(id);
    }
}
