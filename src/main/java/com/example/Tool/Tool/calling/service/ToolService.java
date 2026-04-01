package com.example.Tool.Tool.calling.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.stereotype.Service;
import com.example.Tool.Tool.calling.tools.*;

import java.util.Map;

@Service
public class ToolService {
    private final ChatClient chatClient;

    public ToolService(ChatClient.Builder builder,LoanTools loanTools) {
        this.chatClient = builder
                .defaultSystem("""
                        You are a banking assistant.
                        Help users calculate EMI and answer finance questions.
                        """)
                .defaultTools(loanTools)
                .defaultAdvisors(MessageChatMemoryAdvisor.builder(
                        MessageWindowChatMemory.builder().build()
                ).build())
                .build();
    }

    public String chat(String message,String CId) {
        return chatClient.prompt(message)
                .advisors(a -> a.params(Map.of("chat_memory_conversation_id",CId)))
                .call().content();
    }
}
