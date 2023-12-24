package ru.vsu.cs.sheina.blogservice.configuration;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.Queue;

@Configuration
public class RabbitConfig {
    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public Queue authServiceQueue() {
        return new Queue(RabbitQueues.fromAuthService);
    }

    @Bean
    public Queue fileServiceQueue() {
        return new Queue(RabbitQueues.fromFileService);
    }
}
