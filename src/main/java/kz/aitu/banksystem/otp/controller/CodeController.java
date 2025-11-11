package kz.aitu.banksystem.otp.controller;

import kz.aitu.banksystem.otp.model.dto.request.CodeCheckRequest;
import kz.aitu.banksystem.otp.model.dto.request.CodeGenerateRequest;
import kz.aitu.banksystem.otp.model.dto.response.CodeResponse;
import kz.aitu.banksystem.otp.service.impl.CodeServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("otp")
public class CodeController {
    private final CodeServiceImpl service;

    @Async
    @PostMapping("/generate")
    public ResponseEntity<?> generate(@RequestBody @Validated CodeGenerateRequest request) {
        service.generateCode(request.getReceiver(), request.getType());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/check")
    public ResponseEntity<CodeResponse> check(@RequestBody @Validated CodeCheckRequest request) {
        return ResponseEntity.ok(service.checkReceiverCode(request.getReceiver(), request.getCode()));
    }
}
