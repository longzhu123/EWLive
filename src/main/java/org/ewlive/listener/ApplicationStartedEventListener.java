package org.ewlive.listener;

import com.alibaba.fastjson.JSON;
import org.ewlive.constants.CommonConstants;
import org.ewlive.entity.system.SysDicItem;
import org.ewlive.service.system.SysDicItemService;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * SpringBoot应用启动监听类
 */
@Component
public class ApplicationStartedEventListener implements ApplicationListener<ContextRefreshedEvent> {

    @Resource
    private SysDicItemService sysDicItemService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if (!Objects.isNull(sysDicItemService)) {
            //查询所有的字典项
            List<SysDicItem> sysDicItems = sysDicItemService.getSysDicItemByParams(null).getData();
            //将所有字典项数据放入全局缓存中
            CommonConstants.map.put(CommonConstants.DIC_ITEM_CACHE_KEY, JSON.toJSONString(sysDicItems));
        }
    }
}
