package com.example.Tool.Tool.calling.controller;

import com.example.Tool.Tool.calling.dto.*;
import com.example.Tool.Tool.calling.service.ToolService;
//import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class ToolController {
    private final ToolService toolService;

    public ToolController(ToolService toolService) {
        this.toolService = toolService;
    }

    @PostMapping("/chat")
    public ChatResponse chat(@RequestBody ChatRequest chatrequest){
        String reply = toolService.chat(chatrequest.msg(),chatrequest.CId());
        return new ChatResponse(reply,chatrequest.CId());
    }
}
