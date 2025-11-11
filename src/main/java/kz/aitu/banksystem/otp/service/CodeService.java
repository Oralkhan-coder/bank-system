package kz.aitu.banksystem.otp.service;

import kz.aitu.banksystem.otp.model.dto.response.CodeResponse;
import kz.aitu.banksystem.otp.model.enums.CodeSendType;

public interface CodeService {
    void generateCode(String phoneNumber, CodeSendType type);
    CodeResponse checkReceiverCode(String phoneNumber, String code);
}
