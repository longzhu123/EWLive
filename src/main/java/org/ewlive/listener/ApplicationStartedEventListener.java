package org.ewlive.listener;

import com.alibaba.fastjson.JSON;
import org.ewlive.constants.CommonConstants;
import org.ewlive.entity.SysDicItem;
import org.ewlive.service.SysDicItemService;
import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationListener;

import javax.annotation.Resource;
import java.util.List;

/**
 * SpringBoot应用启动监听类
 */
public class ApplicationStartedEventListener implements ApplicationListener<ApplicationStartingEvent> {

    @Resource
    private SysDicItemService sysDicItemService;

    @Override
    public void onApplicationEvent(ApplicationStartingEvent applicationStartedEvent) {
        //查询所有的字典项
        List<SysDicItem> sysDicItems = sysDicItemService.getSysDicItemByParams(null).getData();
        //将所有字典项数据放入全局缓存中
        CommonConstants.map.put(CommonConstants.DIC_ITEM_CACHE_KEY,JSON.toJSONString(sysDicItems));
    }

}
