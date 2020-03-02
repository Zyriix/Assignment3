package com.bigfans.pricingservice.events;

import com.bigfans.framework.event.EventRepository;
import com.bigfans.framework.kafka.KafkaTemplate;
import com.bigfans.model.event.coupon.CouponCreatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class CouponEventHandler {
    @Autowired
    private KafkaTemplate kafkaTemplate;
    @Autowired
    private EventRepository eventRepository;

    private static final Logger logger = LoggerFactory.getLogger(CouponEventHandler.class);

    @Transactional
    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void on(CouponCreatedEvent event) {
        eventRepository.save(event);
        kafkaTemplate.send(event);
        logger.debug("CouponCreatedEvent invoked");
    }
}
