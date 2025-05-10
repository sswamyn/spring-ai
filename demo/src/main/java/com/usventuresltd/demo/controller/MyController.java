package com.usventuresltd.demo.controller;

import jakarta.annotation.PostConstruct;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {
    @Autowired
    private OpenAiChatModel openAiChatModel;

    private ChatClient chatClient;

    @PostConstruct
    public void init() {
        chatClient = ChatClient.builder(openAiChatModel).build();
    }
//   This did not  work: since the openAiChatModel is not yet initialized before the controller is called
//     Hence the use  of @PostConstruct - see above.  
//    public MyController() {
//        chatClient = ChatClient.builder(openAiChatModel).build();
//
//    }

    @GetMapping("/momJoke")
    public String generateMomJoke() {

        return chatClient.prompt()
                .user("Tell me a mothers day joke")
                .call()
                .content();
    }
}
