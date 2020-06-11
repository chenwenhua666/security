package com.plm.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author : cwh
 * 2019/6/3 0003
 * description ：
 */
@Slf4j
@Component
public class MockQueue {
    private String placeOrder;
    private String completeOrder;

    public String getPlaceOrder() {
        return placeOrder;
    }

    public void setPlaceOrder(String placeOrder) throws Exception {
        new Thread(() -> {
            log.info("收到下单请求，" + placeOrder);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.completeOrder = placeOrder;
            log.info("下单请求处理完毕，" + placeOrder);
        }).start();
    }

    public String getCompleteOrder() {
        return completeOrder;
    }

    public void setCompleteOrder(String completeOrder) {
        this.completeOrder = completeOrder;
    }
}
