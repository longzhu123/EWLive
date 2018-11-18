package org.ewlive.constants;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 通用常量类
 */
public class CommonConstants {

    //全局缓存Map
    public static final Map<String, String> map = new ConcurrentHashMap<>();

    //字典项的缓存key
    public static final String DIC_ITEM_CACHE_KEY = "dic_item_cache";

}
