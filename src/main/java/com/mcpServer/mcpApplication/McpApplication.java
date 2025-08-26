package com.mcpServer.mcpApplication;

import com.mcpServer.mcpApplication.service.ScheduleService;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

@SpringBootApplication
public class McpApplication {

    public static void main(String[] args) {
        try {
            SpringApplication.run(McpApplication.class, args);

            System.out.println("=".repeat(60));
            System.out.println("MCP APPLICATION STARTED");
            System.out.println("=".repeat(60));
            System.out.println("This application now supports BOTH:");
            System.out.println("1. MCP SERVER: Exposes IPL schedule tools");
            System.out.println("2. MCP CLIENT: Can consume external MCP servers");
            System.out.println("");
            System.out.println("CLIENT ENDPOINTS:");
            System.out.println("POST /mcp-client/connect - Connect to MCP server");
            System.out.println("POST /mcp-client/list-tools - List available tools");
            System.out.println("POST /mcp-client/call-tool - Call a specific tool");
            System.out.println("POST /mcp-client/api-call - Make custom API calls");
            System.out.println("=".repeat(60));

        } catch (Exception e) {
            System.err.println("Application failed to start: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }

    @Bean
    public ToolCallbackProvider mcpTools(ScheduleService scheduleService) {
        return MethodToolCallbackProvider.builder().toolObjects(scheduleService).build();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}



/**
@SpringBootApplication
public class McpApplication {

    public static void main(String[] args) {
        try {
            ConfigurableApplicationContext context = SpringApplication.run(McpApplication.class, args);

            System.out.println("=".repeat(60));
            System.out.println("MCP APPLICATION STARTED");
            System.out.println("=".repeat(60));
            System.out.println("This application now supports BOTH:");
            System.out.println("1. MCP SERVER: Exposes IPL schedule tools");
            System.out.println("2. MCP CLIENT: Can consume external MCP servers");
            System.out.println("");
            System.out.println("CLIENT ENDPOINTS:");
            System.out.println("POST /mcp-client/connect - Connect to MCP server");
            System.out.println("POST /mcp-client/list-tools - List available tools");
            System.out.println("POST /mcp-client/call-tool - Call a specific tool");
            System.out.println("POST /mcp-client/api-call - Make custom API calls");
            System.out.println("=".repeat(60));

            // Keep the application running
            synchronized (McpApplication.class) {
                McpApplication.class.wait();
            }
        } catch (Exception e) {
            System.err.println("Application failed to start: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }

    @Bean
    public ToolCallbackProvider mcpTools(ScheduleService scheduleService) {
        return MethodToolCallbackProvider.builder().toolObjects(scheduleService).build();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}*/