package com.mcpServer.mcpApplication.controller;

import com.mcpServer.mcpApplication.service.McpClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/mcp-client")
public class McpClientController {

    @Autowired
    private McpClientService mcpClientService;

    /**
     * Test self-connection to default MCP server
     */
    @GetMapping("/test")
    public ResponseEntity<?> testSelfConnection() {
        try {
            Map<String, Object> result = mcpClientService.connectToMCPServer();
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(Map.of("error", e.getMessage()));
        }
    }

    /**
     * Get all matches using client (self-call)
     */
    @GetMapping("/matches")
    public ResponseEntity<?> getAllMatches() {
        try {
            Object result = mcpClientService.callTool("getAllMatches", new HashMap<>());
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(Map.of("error", e.getMessage()));
        }
    }

    /**
     * Get match by number using client
     */
    @GetMapping("/matches/{matchNumber}")
    public ResponseEntity<?> getMatchByNumber(@PathVariable int matchNumber) {
        try {
            Map<String, Object> args = Map.of("matchNumber", matchNumber);
            Object result = mcpClientService.callTool("getMatchByNumber", args);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(Map.of("error", e.getMessage()));
        }
    }

    /**
     * Connect to an external MCP server
     */
    @PostMapping("/connect")
    public ResponseEntity<Map<String, Object>> connectToServer(@RequestBody Map<String, String> request) {
        try {
            String serverUrl = request.get("serverUrl");
            if (serverUrl == null || serverUrl.trim().isEmpty()) {
                // Use default server if no URL provided
                Map<String, Object> result = mcpClientService.connectToMCPServer();
                return ResponseEntity.ok(result);
            }

            Map<String, Object> result = mcpClientService.connectToMCPServer(serverUrl);
            return ResponseEntity.ok(result);

        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(Map.of("error", e.getMessage()));
        }
    }

    /**
     * List all tools available on the MCP server
     */
    @PostMapping("/list-tools")
    public ResponseEntity<?> listTools(@RequestBody(required = false) Map<String, String> request) {
        try {
            List<Map<String, Object>> tools;

            if (request != null && request.get("serverUrl") != null) {
                String serverUrl = request.get("serverUrl");
                tools = mcpClientService.listTools(serverUrl);
            } else {
                // Use default server
                tools = mcpClientService.listTools();
            }

            return ResponseEntity.ok(Map.of("tools", tools));

        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(Map.of("error", e.getMessage()));
        }
    }

    /**
     * Call a specific tool on the MCP server
     */
    @PostMapping("/call-tool")
    public ResponseEntity<?> callTool(@RequestBody Map<String, Object> request) {
        try {
            String serverUrl = (String) request.get("serverUrl");
            String toolName = (String) request.get("toolName");
            @SuppressWarnings("unchecked")
            Map<String, Object> arguments = (Map<String, Object>) request.get("arguments");

            if (toolName == null) {
                return ResponseEntity.badRequest()
                        .body(Map.of("error", "toolName is required"));
            }

            Object result;
            if (serverUrl != null && !serverUrl.trim().isEmpty()) {
                result = mcpClientService.callTool(serverUrl, toolName, arguments != null ? arguments : new HashMap<>());
            } else {
                // Use default server
                result = mcpClientService.callTool(toolName, arguments != null ? arguments : new HashMap<>());
            }

            return ResponseEntity.ok(result);

        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(Map.of("error", e.getMessage()));
        }
    }

    /**
     * Make a custom API call to any endpoint on the MCP server
     */
    @PostMapping("/api-call")
    public ResponseEntity<?> makeAPICall(@RequestBody Map<String, Object> request) {
        try {
            String serverUrl = (String) request.get("serverUrl");
            String endpoint = (String) request.get("endpoint");
            @SuppressWarnings("unchecked")
            Map<String, Object> payload = (Map<String, Object>) request.get("payload");

            if (endpoint == null) {
                return ResponseEntity.badRequest()
                        .body(Map.of("error", "endpoint is required"));
            }

            Map<String, Object> result;
            if (serverUrl != null && !serverUrl.trim().isEmpty()) {
                result = mcpClientService.makeAPICall(serverUrl, endpoint, payload);
            } else {
                // Use default server
                result = mcpClientService.makeAPICall(endpoint, payload);
            }

            return ResponseEntity.ok(result);

        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(Map.of("error", e.getMessage()));
        }
    }

    /**
     * Get default server URL
     */
    @GetMapping("/server-info")
    public ResponseEntity<Map<String, String>> getServerInfo() {
        return ResponseEntity.ok(Map.of(
                "defaultServerUrl", mcpClientService.getDefaultServerUrl(),
                "status", "running"
        ));
    }
}


/**
package com.mcpServer.mcpApplication.controller;


import com.mcpServer.mcpApplication.service.McpClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/mcp-client")
public class McpClientController {

    @Autowired
    private McpClientService mcpClientService;


     // Connect to an external MCP server

    @PostMapping("/connect")
    public ResponseEntity<Map<String, Object>> connectToServer(@RequestBody Map<String, String> request) {
        try {
            String serverUrl = request.get("serverUrl");
            if (serverUrl == null || serverUrl.trim().isEmpty()) {
                return ResponseEntity.badRequest().build();
            }

            Map<String, Object> result = mcpClientService.connectToMCPServer(serverUrl);
            return ResponseEntity.ok(result);

        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(Map.of("error", e.getMessage()));
        }
    }


     // List all tools available on the external MCP server
    @PostMapping("/list-tools")
    public ResponseEntity<?> listTools(@RequestBody Map<String, String> request) {
        try {
            String serverUrl = request.get("serverUrl");
            if (serverUrl == null || serverUrl.trim().isEmpty()) {
                return ResponseEntity.badRequest()
                        .body(Map.of("error", "serverUrl is required"));
            }

            List<Map<String, Object>> tools = mcpClientService.listTools(serverUrl);
            return ResponseEntity.ok(Map.of("tools", tools));

        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(Map.of("error", e.getMessage()));
        }
    }


     // Call a specific tool on the external MCP server
    @PostMapping("/call-tool")
    public ResponseEntity<?> callTool(@RequestBody Map<String, Object> request) {
        try {
            String serverUrl = (String) request.get("serverUrl");
            String toolName = (String) request.get("toolName");
            @SuppressWarnings("unchecked")
            Map<String, Object> arguments = (Map<String, Object>) request.get("arguments");

            if (serverUrl == null || toolName == null) {
                return ResponseEntity.badRequest()
                        .body(Map.of("error", "serverUrl and toolName are required"));
            }

            // The service now returns an Object, so the controller must handle it.
            // We use a generic ResponseEntity<?> to accommodate different return types.
            Object result = mcpClientService.callTool(serverUrl, toolName, arguments);
            return ResponseEntity.ok(result);

        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(Map.of("error", e.getMessage()));
        }
    }



     //Make a custom API call to any endpoint on the MCP server
    @PostMapping("/api-call")
    public ResponseEntity<?> makeAPICall(@RequestBody Map<String, Object> request) {
        try {
            String serverUrl = (String) request.get("serverUrl");
            String endpoint = (String) request.get("endpoint");
            @SuppressWarnings("unchecked")
            Map<String, Object> payload = (Map<String, Object>) request.get("payload");

            if (serverUrl == null || endpoint == null) {
                return ResponseEntity.badRequest()
                        .body(Map.of("error", "serverUrl and endpoint are required"));
            }

            Map<String, Object> result = mcpClientService.makeAPICall(serverUrl, endpoint, payload);
            return ResponseEntity.ok(result);

        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(Map.of("error", e.getMessage()));
        }
    }
}*/