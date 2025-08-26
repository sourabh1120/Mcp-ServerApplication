package com.mcpServer.mcpApplication.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

@Service
public class McpClientService {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    // Default server URL from configuration
    @Value("${mcp.server.base-url:http://localhost:8082}")
    private String defaultMcpServerUrl;

    public McpClientService() {
        this.restTemplate = new RestTemplate();
        this.objectMapper = new ObjectMapper();
    }

    /**
     * Connect to an MCP server and get available tools
     * If no URL provided, uses default server URL
     */
    public Map<String, Object> connectToMCPServer() {
        return connectToMCPServer(defaultMcpServerUrl);
    }

    public Map<String, Object> connectToMCPServer(String mcpServerUrl) {
        try {
            String initUrl = mcpServerUrl + "/api/ipl/schedule/tools/list";

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            Map<String, Object> initRequest = new HashMap<>();
            initRequest.put("protocolVersion", "2024-11-05");
            initRequest.put("capabilities", Map.of("tools", Map.of()));
            initRequest.put("clientInfo", Map.of(
                    "name", "SpringBoot-MCP-Client",
                    "version", "1.0.0"
            ));

            HttpEntity<Map<String, Object>> request = new HttpEntity<>(initRequest, headers);
            ResponseEntity<String> response = restTemplate.postForEntity(initUrl, request, String.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                return objectMapper.readValue(response.getBody(), Map.class);
            }

            throw new RuntimeException("Failed to initialize MCP server connection");

        } catch (Exception e) {
            throw new RuntimeException("Error connecting to MCP server: " + e.getMessage(), e);
        }
    }

    /**
     * List all available tools from an MCP server
     */
    public List<Map<String, Object>> listTools() {
        return listTools(defaultMcpServerUrl);
    }

    public List<Map<String, Object>> listTools(String mcpServerUrl) {
        try {
            String toolsUrl = mcpServerUrl + "/api/ipl/schedule/tools/list";

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            Map<String, Object> toolsRequest = new HashMap<>();

            HttpEntity<Map<String, Object>> request = new HttpEntity<>(toolsRequest, headers);
            ResponseEntity<String> response = restTemplate.postForEntity(toolsUrl, request, String.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                JsonNode jsonResponse = objectMapper.readTree(response.getBody());
                JsonNode tools = jsonResponse.get("tools");

                List<Map<String, Object>> toolsList = new ArrayList<>();
                if (tools != null && tools.isArray()) {
                    for (JsonNode tool : tools) {
                        Map<String, Object> toolMap = new HashMap<>();
                        toolMap.put("name", tool.asText());
                        toolsList.add(toolMap);
                    }
                }
                return toolsList;
            }

            throw new RuntimeException("Failed to list tools from MCP server");

        } catch (Exception e) {
            throw new RuntimeException("Error listing tools: " + e.getMessage(), e);
        }
    }

    /**
     * Call a specific tool on the default MCP server
     */
    public Object callTool(String toolName, Map<String, Object> arguments) {
        return callTool(defaultMcpServerUrl, toolName, arguments);
    }

    /**
     * Call a specific tool on the MCP server
     */
    public Object callTool(String mcpServerUrl, String toolName, Map<String, Object> arguments) {
        try {
            String callUrl;
            ResponseEntity<String> response;

            switch (toolName) {
                case "getAllMatches":
                    callUrl = mcpServerUrl + "/api/ipl/schedule";
                    response = restTemplate.getForEntity(callUrl, String.class);
                    return objectMapper.readValue(response.getBody(), new TypeReference<List<Map<String, Object>>>() {});

                case "getMatchByNumber":
                    callUrl = mcpServerUrl + "/api/ipl/schedule/match/" + arguments.get("matchNumber");
                    response = restTemplate.getForEntity(callUrl, String.class);
                    return objectMapper.readValue(response.getBody(), new TypeReference<Map<String, Object>>() {});

                case "getMatchesByTeam":
                    callUrl = mcpServerUrl + "/api/ipl/schedule/team/" + arguments.get("teamName");
                    response = restTemplate.getForEntity(callUrl, String.class);
                    return objectMapper.readValue(response.getBody(), new TypeReference<List<Map<String, Object>>>() {});

                case "getMatchesByDate":
                    callUrl = mcpServerUrl + "/api/ipl/schedule/date/" + arguments.get("date");
                    response = restTemplate.getForEntity(callUrl, String.class);
                    return objectMapper.readValue(response.getBody(), new TypeReference<List<Map<String, Object>>>() {});

                case "getMatchesByVenue":
                    callUrl = mcpServerUrl + "/api/ipl/schedule/venue/" + arguments.get("venue");
                    response = restTemplate.getForEntity(callUrl, String.class);
                    return objectMapper.readValue(response.getBody(), new TypeReference<List<Map<String, Object>>>() {});

                default:
                    throw new UnsupportedOperationException("Tool '" + toolName + "' is not supported by this client.");
            }

        } catch (Exception e) {
            throw new RuntimeException("Error calling tool '" + toolName + "': " + e.getMessage(), e);
        }
    }

    /**
     * Generic method to make any API call to default MCP server
     */
    public Map<String, Object> makeAPICall(String endpoint, Map<String, Object> payload) {
        return makeAPICall(defaultMcpServerUrl, endpoint, payload);
    }

    /**
     * Generic method to make any API call to MCP server
     */
    public Map<String, Object> makeAPICall(String mcpServerUrl, String endpoint, Map<String, Object> payload) {
        try {
            String fullUrl = mcpServerUrl + endpoint;

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<Map<String, Object>> request = new HttpEntity<>(payload, headers);
            ResponseEntity<String> response = restTemplate.postForEntity(fullUrl, request, String.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                return objectMapper.readValue(response.getBody(), Map.class);
            }

            throw new RuntimeException("API call failed with status: " + response.getStatusCode());

        } catch (Exception e) {
            throw new RuntimeException("Error making API call: " + e.getMessage(), e);
        }
    }

    /**
     * Get the default MCP server URL
     */
    public String getDefaultServerUrl() {
        return defaultMcpServerUrl;
    }
}

