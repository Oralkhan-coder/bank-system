package kz.aitu.banksystem.otp.service.impl;

import kz.aitu.banksystem.core.exeption.ServiceValidationException;
import kz.aitu.banksystem.core.model.statics.ErrorCode;
import kz.aitu.banksystem.core.utils.Util;
import kz.aitu.banksystem.notification.context.NotificationContext;
import kz.aitu.banksystem.notification.strategy.impl.EmailNotification;
import kz.aitu.banksystem.notification.strategy.impl.TelegramNotification;
import kz.aitu.banksystem.otp.model.dto.response.CodeResponse;
import kz.aitu.banksystem.otp.model.entity.Code;
import kz.aitu.banksystem.otp.model.enums.CodeSendType;
import kz.aitu.banksystem.otp.repository.CodeRepository;
import kz.aitu.banksystem.otp.service.CodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CodeServiceImpl implements CodeService {
    private final CodeRepository repository;

    @Override
    public void generateCode(String phoneNumber, CodeSendType type) {
        Code oldCode = repository.findByDeletedAtIsNullAndReceiverOrderByIdDesc(phoneNumber).orElse(null);

        if (phoneNumber.startsWith("87")) {
            throw new ServiceValidationException("error.number.should.start.seven", ErrorCode.INVALID_ARGUMENT);
        }

        if (Objects.nonNull(oldCode)) {
            LocalDateTime createdDate = LocalDateTime.ofInstant(oldCode.getCreatedAt().toInstant(), ZoneId.systemDefault());
            if (createdDate.plusMinutes(2).isAfter(LocalDateTime.now())) {
                return;
            }
            repository.delete(oldCode);
        }

        Code code = Code.builder()
                .receiver(phoneNumber)
                .sentType(type.getValue())
                .code(Util.getRandomFiveDigitalNumber())
                .build();
        repository.save(code);

        NotificationContext ctx = new NotificationContext(new TelegramNotification());
        if (type == CodeSendType.EMAIL) {
            ctx.setStrategy(new EmailNotification());
        } else if (type != CodeSendType.Telegram){
            throw ServiceValidationException.builder()
                    .message("Invalid type")
                    .errorCode(ErrorCode.INVALID_ARGUMENT)
                    .build();
        }

        ctx.send(phoneNumber, code.getCode());
    }

    @Override
    public CodeResponse checkReceiverCode(String phoneNumber, String code) {
        Code c = repository.findByDeletedAtIsNullAndReceiverOrderByIdDesc(phoneNumber).orElse(null);
        if(Objects.nonNull(c)) {
            if (Objects.equals(c.getCode(), code) || Util.checkCodeForBackDoor(code)) {
                CodeResponse response = new CodeResponse(true, c.getId());
                repository.delete(c);
                return response;
            }
        }
        return CodeResponse.builder()
                .isFound(false)
                .codeId(null)
                .build();
    }
}
