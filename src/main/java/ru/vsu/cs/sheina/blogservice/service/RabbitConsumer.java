package ru.vsu.cs.sheina.blogservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import ru.vsu.cs.sheina.blogservice.configuration.RabbitQueues;
import ru.vsu.cs.sheina.blogservice.dto.fields.IdDTO;
import ru.vsu.cs.sheina.blogservice.dto.rabbitmq.UrlDTO;

@Service
@RequiredArgsConstructor
public class RabbitConsumer {

    private final UserService userService;
    private final PostService postService;

    @RabbitListener(queues = RabbitQueues.fromAuthService)
    public void createUser(@RequestBody IdDTO idDTO) {
        userService.createUser(idDTO);
    }

    @RabbitListener(queues = RabbitQueues.fromFileService)
    public void changeUrl(@RequestBody UrlDTO urlDTO) {
        postService.changeUrl(urlDTO);
    }
}
