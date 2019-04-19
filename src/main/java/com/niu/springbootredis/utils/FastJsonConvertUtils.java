package com.niu.springbootredis.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: niuhaijun
 * @Date: 2019-04-19 12:43
 * @Version 1.0
 */
public class FastJsonConvertUtils {

  private final static Logger logger = LoggerFactory.getLogger(FastJsonConvertUtils.class);

  private final static SerializerFeature[] featuresWithNullValue = {
      SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullBooleanAsFalse,
      SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteNullNumberAsZero,
      SerializerFeature.WriteNullStringAsEmpty};

  /**
   * JsonString 转换 Object
   */
  public static <T> T convertJSONToObject(String data, Class<T> clzss) {

    if (data == null || clzss == null) {
      return null;
    }
    try {
      return JSON.parseObject(data, clzss);
    } catch (Exception e) {
      logger.error("json string to object error，data=" + data, e);
      throw e;
    }
  }

  /**
   * JsonString 转换 List<Object>
   */
  public static <T> List<T> convertJSONToArray(String data, Class<T> clzss) {

    if (data == null || clzss == null) {
      return null;
    }
    try {
      return JSON.parseArray(data, clzss);
    } catch (Exception e) {
      logger.error("json string to list error，data=" + data, e);
      throw e;
    }
  }

  /**
   * Object to JsonString
   */
  public static String convertObjectToJSON(Object obj) {

    if (obj == null) {
      return null;
    }
    try {
      return JSON.toJSONString(obj);
    } catch (Exception e) {
      logger.error("json object to string error，object=" + obj, e);
      throw e;
    }
  }

  /**
   * Object to JsonString
   */
  public static String convertObjectToJSONWithNullValue(Object obj) {

    if (obj == null) {
      return null;
    }
    try {
      return JSON.toJSONString(obj, featuresWithNullValue);
    } catch (Exception e) {
      logger.error("json object to string error，object=" + obj, e);
      throw e;
    }
  }
}

