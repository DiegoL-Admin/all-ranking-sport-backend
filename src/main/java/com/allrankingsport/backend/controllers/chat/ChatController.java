package com.allrankingsport.backend.controllers.chat;

import com.allrankingsport.backend.dto.chat.ChatRequest;
import com.allrankingsport.backend.dto.chat.ChatResponse;
import com.allrankingsport.backend.services.gemini.GeminiService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/chat")
public class ChatController {

    private final GeminiService geminiService;

    public ChatController(GeminiService geminiService) {
        this.geminiService = geminiService;
    }

    @PostMapping
    @PreAuthorize("isAuthenticated()") // Only authenticated users can chat with AI
    public ResponseEntity<ChatResponse> chatWithGemini(@Valid @RequestBody ChatRequest request) throws IOException {
        String aiResponse = geminiService.getGeminiResponse(request.getMessage());
        return ResponseEntity.ok(new ChatResponse(aiResponse));
    }
}
