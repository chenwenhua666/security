package com.plm.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @author : cwh
 * 2019/6/3 0003
 * description ：
 */
@Slf4j
@Component
public class QueueListener implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private MockQueue mockQueue;

    @Autowired
    private DeferredResultHolder deferredResultHolder;


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        new Thread(() -> {
            while (true) {
                if (!StringUtils.isEmpty(mockQueue.getCompleteOrder())) {
                    String orderNumber = mockQueue.getCompleteOrder();
                    log.info("返回订单处理结果" + orderNumber);
                    deferredResultHolder.getMap().get(orderNumber).setResult("order success");
                    mockQueue.setCompleteOrder(null);
                } else {
                    try {
                        Thread.sleep(100);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

    }
}
