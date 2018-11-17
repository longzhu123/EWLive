package org.ewlive.util;

import com.alibaba.fastjson.JSON;
import org.ewlive.aop.Dic;
import org.ewlive.constants.CommonConstants;
import org.ewlive.constants.ExceptionConstants;
import org.ewlive.entity.SysDic;
import org.ewlive.entity.SysDicItem;
import org.ewlive.exception.ServiceException;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 字典转换工具类(将字典项code变成中文描述)
 */
public class DicConvertUtil {

    /**
     * 将对象中的字典项code变成中文描述
     * 并返回一个全新的对象
     *
     * @param obj   需要转换的对象
     * @param clazz 转换成啥类型的对象
     * @param <T>
     */
    public static <T> T convertObjDicDesc(T obj, Class<T> clazz){
        Field[] fields = clazz.getDeclaredFields();
        List<Field> fieldList = Arrays.asList(fields);
        //将有@Dic修饰注解的属性过滤出来
        fieldList = fieldList.parallelStream().filter(item -> !Objects.isNull(item.getAnnotation(Dic.class))).collect(Collectors.toList());
        //获取缓存中字典项数据
        List<SysDicItem> sysDicItems = JSON.parseArray(CommonConstants.map.get(CommonConstants.DIC_ITEM_CACHE_KEY), SysDicItem.class);
        //将List转换为Map
        Map<String, String> dicMap = new HashMap<>();
        sysDicItems.forEach(item -> {
            //key格式:dicId,dicItemCode
            //value:dicItemName
            dicMap.put(item.getDicId() + "," + item.getDicItemCode(), item.getDicItemName());
        });

        try {
            //获取字典项的中文描述
            for (Field field : fieldList) {
                field.setAccessible(true);

                //获取字典码的中文描述
                String dicId = field.getAnnotation(Dic.class).dicId();
                String fileValue = field.get(obj).toString();
                String dicMapKey = dicId + "," + fileValue;
                String dicDesc = dicMap.get(dicMapKey);

                //将中文描述赋值给对象的Desc属性
                Field descFiled = clazz.getDeclaredField(field.getName() + "Desc");
                descFiled.setAccessible(true);
                descFiled.set(obj, dicDesc);
            }
            return obj;
        } catch (Exception e) {
            throw  new ServiceException(ExceptionConstants.SYSTEM_ERROR);
        }
    }

}

