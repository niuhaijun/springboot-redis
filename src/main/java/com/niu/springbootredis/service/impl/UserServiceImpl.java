package com.niu.springbootredis.service.impl;


import static com.niu.springbootredis.utils.ConstantKeys.USER_KEY_PREFIX;

import com.niu.springbootredis.config.RedisClientTemplate;
import com.niu.springbootredis.controller.param.UserPara;
import com.niu.springbootredis.controller.vo.UserVO;
import com.niu.springbootredis.mapper.UserMapper;
import com.niu.springbootredis.model.User;
import com.niu.springbootredis.model.UserExample;
import com.niu.springbootredis.service.UserService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: niuhaijun
 * @Date: 2019-04-11 15:14
 * @Version 1.0
 */
@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserMapper userMapper;

  @Autowired
  private RedisClientTemplate redisClientTemplate;

  @Override
  public Integer add(UserPara userPara) {

    redisClientTemplate.del(USER_KEY_PREFIX + "" + userPara.toString());
    Date date = new Date();
    userPara.setCreateTime(date);
    userPara.setUpdateTime(date);

    User user = new User();
    BeanUtils.copyProperties(userPara, user);
    return userMapper.insertSelective(user);
  }

  @Override
  public Integer update(UserPara userPara) {

    redisClientTemplate.del(USER_KEY_PREFIX + "" + userPara.toString());

    userPara.setUpdateTime(new Date());
    User user = new User();
    BeanUtils.copyProperties(userPara, user);

    UserExample example = new UserExample();
    example.createCriteria().andUuidEqualTo(userPara.getUuid());
    return userMapper.updateByExampleSelective(user, example);
  }

  @Override
  public Integer delete(UserPara userPara) {

    redisClientTemplate.del(USER_KEY_PREFIX + "" + userPara.toString());

    return userMapper.deleteByPrimaryKey(userPara.getUuid());
  }

  @Override
  public List<UserVO> select(UserPara userPara) {

    String key = USER_KEY_PREFIX + "" + userPara.toString();

    boolean exist = redisClientTemplate.exists(key);
    if (exist) {
      return redisClientTemplate.getByKey(key);
    }

    UserExample example = new UserExample();
    example.setOrderByClause("update_time desc");
    example.createCriteria().andAgeLessThan(userPara.getAge());

    List<User> userList = userMapper.selectByExample(example);
    ArrayList<UserVO> result = new ArrayList<>(userList.size());
    userList.forEach(t -> {
      UserVO userVO = new UserVO();
      BeanUtils.copyProperties(t, userVO);
      result.add(userVO);
    });

    redisClientTemplate.set(key, result, 60);

    return result;
  }

}
