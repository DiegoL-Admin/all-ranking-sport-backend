package com.allrankingsport.backend.dto.chat;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;

@Data
public class ChatRequest {
    @NotBlank
    private String message;
}
