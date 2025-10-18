package com.project.reservations_hotel.listener;

import com.project.reservations_hotel.model.kafka.StatEvent;
import com.project.reservations_hotel.service.StatisticService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaHandler {

    private final StatisticService statisticService;

    @KafkaListener(topics = "${app.kafka.userCreatedTopic}",
            groupId = "${app.kafka.kafkaMessageGroup}",
            containerFactory = "concurrentKafkaListenerContainerFactory")
    public void listenCreatedTopic(@Payload StatEvent message) {
        statisticService.save(message);
    }

    @KafkaListener(topics = "${app.kafka.bookingTopic}",
            groupId = "${app.kafka.kafkaMessageGroup}",
            containerFactory = "concurrentKafkaListenerContainerFactory")
    public void listenBookingTopic(@Payload StatEvent message) {
        statisticService.save(message);
    }


}
