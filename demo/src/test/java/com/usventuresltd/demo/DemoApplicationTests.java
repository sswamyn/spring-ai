package com.usventuresltd.demo;

import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    private OpenAiChatModel openAiChatModel;

    @Test
    void contextLoads() {
    }

    @Test
    void simpleOpenAiQuery() {
        // Create a chat client using the OpenAiChatModel.
        // Using the ChatClient builder(model) to create a chat client.
        ChatClient chatClient = ChatClient.builder(openAiChatModel).build();
        String response = chatClient.prompt("Tell me mothers day joke")
                .call()
                .content();
        System.out.println("Response: " + response);

    }

}
