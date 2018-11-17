package org.ewlive.util;

import com.alibaba.fastjson.JSON;
import org.ewlive.aop.Dic;
import org.ewlive.constants.CommonConstants;
import org.ewlive.entity.SysDic;
import org.ewlive.entity.SysDicItem;

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
    public static <T> void convertObjDicDesc(Object obj, Class<T> clazz) {
        Field[] fields = clazz.getDeclaredFields();
        List<Field> fieldList = Arrays.asList(fields);
        //将有@Dic修饰注解的属性过滤出来
        fieldList = fieldList.parallelStream().filter(item->!Objects.isNull(item.getAnnotation(Dic.class))).collect(Collectors.toList());
        //获取缓存中字典项数据
        List<SysDicItem> sysDicItems = JSON.parseArray(CommonConstants.map.get(CommonConstants.DIC_ITEM_CACHE_KEY), SysDicItem.class);
        //将List转换为Map
        Map<String, String> dicMap = new HashMap<>();
        sysDicItems.forEach(item->{
            //key格式:id,dicItemCode
            //value:dicItemName
            dicMap.put(item.getId()+","+item.getDicItemCode(),item.getDicItemName());
        });

        try {
            //获取id属性值
            String id = clazz.getDeclaredField("id").get(obj).toString();
            for (Field field : fieldList) {
                field.setAccessible(true);
                String fileValue = field.get(obj).toString();
                String dicMapKey = id+","+fileValue;
                String dicDesc = dicMap.get(dicMapKey);
                System.out.println(field.getName()+","+dicDesc);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
