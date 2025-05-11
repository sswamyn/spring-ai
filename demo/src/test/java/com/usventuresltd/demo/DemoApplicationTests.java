package com.usventuresltd.demo;

import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    private OpenAiChatModel openAiChatModel;

    @Value("classpath:movie_prompt.st")
    private Resource promptTemplate;

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

    @Test
    void promtpTemplate() {
        String answer = ChatClient.create(openAiChatModel).prompt()
                .user( u -> u
                        .text("Tell me a the name of 5 movies whose soundtrack was composed by {composer}")
                        .param("composer", "John Williams"))
                .call()
                .content();
        System.out.println("Response: " + answer);
    }

    @Test
    void promtpTemplateResourceTemplate() {
        String answer = ChatClient.create(openAiChatModel).prompt()
                .user( u -> u
                        .text(promptTemplate)
                        .param("number", "8")
                        .param("composer", "Michael Giacchino"))
                .call()
                .content();
        System.out.println("Response: " + answer);
    }

    // Using Advisor
    @Test
    void simpleOpenAiQueryWithAdvisor() {
        // Create a chat client using the OpenAiChatModel.
        // Using the ChatClient builder(model) to create a chat client.
        ChatClient chatClient = ChatClient.builder(openAiChatModel).build();
        String response = chatClient.prompt("Tell me dad joke")
                .advisors(new SimpleLoggerAdvisor())
                .call()
                .content();
        System.out.println("Response: " + response);

    }
}
