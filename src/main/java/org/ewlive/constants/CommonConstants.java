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

    //最顶层的菜单id
    public static final String TOP_MENU_ID = "099ce9a9f85b4ef896da216d33e28ce3";


    //Swgger 相关常量配置
    public  static  final  String API_TITLE = "EwLive 接口文档";

    public  static  final  String API_DESC = "EwLive 接口文档";

    public  static  final  String API_URL = "http://127.0.0.1/";

    public  static  final  String API_VERSION = "1.0";


}
