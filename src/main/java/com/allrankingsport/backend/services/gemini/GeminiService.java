package com.allrankingsport.backend.services.gemini;

import com.allrankingsport.backend.dto.ProductDto;
import com.allrankingsport.backend.services.ProductService;
import com.google.cloud.vertexai.VertexAI;
import com.google.cloud.vertexai.api.GenerateContentResponse;
import com.google.cloud.vertexai.generativeai.GenerativeModel;
import com.google.cloud.vertexai.generativeai.ResponseHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GeminiService {

    @Value("${gemini.project.id}")
    private String projectId;

    @Value("${gemini.location}")
    private String location;

    private final ProductService productService;

    public GeminiService(ProductService productService) {
        this.productService = productService;
    }

    public String getGeminiResponse(String userMessage) throws IOException {
        // Fetch product context
        List<ProductDto> products = productService.getAllProducts();
        String productContext = products.stream()
                .map(p -> String.format("ID: %d, Name: %s, Description: %s, Price: %.2f, Image: %s",
                        p.getId(), p.getName(), p.getDescription(), p.getPrice(), p.getImageUrl()))
                .collect(Collectors.joining("\n"));

        // Construct the prompt with product context
        String prompt = String.format(
                "You are an AI assistant for an e-commerce store called All Ranking Sport. " +
                        "Your goal is to help users with product-related queries. " +
                        "Here are the products available in the store:\n%s\n\nUser query: %s\n\nBased on the available products, please answer the user's query. If the query is not related to products, politely state that you can only assist with product-related questions.",
                productContext, userMessage
        );

        try (VertexAI vertexAi = new VertexAI(projectId, location)) {
            GenerativeModel model = new GenerativeModel("gemini-2.0-flash-001", vertexAi);
            GenerateContentResponse response = model.generateContent(prompt);
            return ResponseHandler.getText(response);
        }
    }
}
