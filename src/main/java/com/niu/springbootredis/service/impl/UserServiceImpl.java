package com.niu.springbootredis.service.impl;


import com.niu.springbootredis.config.RedisClientTemplate;
import com.niu.springbootredis.controller.param.UserPara;
import com.niu.springbootredis.controller.vo.UserVO;
import com.niu.springbootredis.mapper.UserMapper;
import com.niu.springbootredis.model.User;
import com.niu.springbootredis.model.UserExample;
import com.niu.springbootredis.service.UserService;
import com.niu.springbootredis.utils.FastJsonConvertUtils;
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

    Date date = new Date();
    userPara.setCreateTime(date);
    userPara.setUpdateTime(date);

    User user = new User();
    BeanUtils.copyProperties(userPara, user);
    return userMapper.insertSelective(user);
  }

  @Override
  public Integer update(UserPara userPara) {

    userPara.setUpdateTime(new Date());
    User user = new User();
    BeanUtils.copyProperties(userPara, user);

    UserExample example = new UserExample();
    example.createCriteria().andUuidEqualTo(userPara.getUuid());
    return userMapper.updateByExampleSelective(user, example);
  }

  @Override
  public Integer delete(UserPara userPara) {

    return userMapper.deleteByPrimaryKey(userPara.getUuid());
  }

  @Override
  public List<UserVO> select(UserPara userPara) {

    String key = "UserServiceImpl#select#" + userPara.getAge().toString();
    boolean exist = redisClientTemplate.exists(key);
    if (exist) {
      return FastJsonConvertUtils.convertJSONToArray(redisClientTemplate.get(key), UserVO.class);
    }

    UserExample example = new UserExample();
    example.setOrderByClause("update_time desc");
    example.createCriteria().andAgeLessThan(userPara.getAge());

    List<User> userList = userMapper.selectByExample(example);
    List<UserVO> result = new ArrayList<>(userList.size());
    userList.forEach(t -> {
      UserVO userVO = new UserVO();
      BeanUtils.copyProperties(t, userVO);
      result.add(userVO);
    });

    redisClientTemplate.setex(key, 30, FastJsonConvertUtils.convertObjectToJSON(result));

    return result;
  }

}
