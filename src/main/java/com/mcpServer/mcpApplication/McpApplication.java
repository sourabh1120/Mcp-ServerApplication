package com.mcpServer.mcpApplication;

import com.mcpServer.mcpApplication.service.ScheduleService;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

@SpringBootApplication
public class McpApplication {

	public static void main(String[] args) {
		try {
			ConfigurableApplicationContext context = SpringApplication.run(McpApplication.class, args);

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

}

