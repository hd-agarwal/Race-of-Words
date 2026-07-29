package org.example.wordsServer.controller;

import lombok.AllArgsConstructor;
import org.example.wordsServer.service.InitializerService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/admin")
@AllArgsConstructor
public class AdminController {
    private InitializerService initializerService;

    @PostMapping("/initialize")
    public ResponseEntity<?> initDB() {
        initializerService.initializeWordsNode();
        return new ResponseEntity<>(HttpStatusCode.valueOf(200));
    }
}
