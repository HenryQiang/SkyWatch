package com.example.skywatch.demos.web;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class BasicController {

    // 简单的欢迎接口，GET请求
    @GetMapping("/welcome")
    public String welcome() {
        return "欢迎使用 SkyWatch 应用服务!";
    }

    // 带路径变量的接口
    @GetMapping("/user/{id}")
    public Map<String, Object> getUserById(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        response.put("id", id);
        response.put("name", "用户" + id);
        response.put("email", "user" + id + "@example.com");
        response.put("message", "成功获取用户信息");
        return response;
    }

    // 带查询参数的接口
    @GetMapping("/search")
    public Map<String, Object> searchUsers(@RequestParam String keyword,
                                           @RequestParam(defaultValue = "1") int page) {
        Map<String, Object> response = new HashMap<>();
        response.put("keyword", keyword);
        response.put("page", page);
        response.put("results", "搜索关键词 '" + keyword + "' 的第 " + page + " 页结果");
        return response;
    }

    // 处理POST请求，接收JSON数据
    @PostMapping("/user")
    public Map<String, Object> createUser(@RequestBody Map<String, Object> userData) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "用户创建成功");
        response.put("data", userData);
        response.put("id", System.currentTimeMillis()); // 模拟生成ID
        return response;
    }

    // 健康检查端点（虽然Actuator已有，但这是一个自定义示例）
    @GetMapping("/health")
    public Map<String, Object> healthCheck() {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "UP");
        response.put("service", "SkyWatch");
        response.put("timestamp", System.currentTimeMillis());
        return response;
    }
}
