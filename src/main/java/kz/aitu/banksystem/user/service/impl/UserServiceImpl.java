package kz.aitu.banksystem.user.service.impl;

import jakarta.transaction.Transactional;
import kz.aitu.banksystem.core.exeption.ServiceValidationException;
import kz.aitu.banksystem.core.model.statics.ErrorCode;
import kz.aitu.banksystem.user.converter.UserConverter;
import kz.aitu.banksystem.user.model.dto.UserViewResponse;
import kz.aitu.banksystem.user.model.entity.User;
import kz.aitu.banksystem.user.repository.UserRepository;
import kz.aitu.banksystem.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static kz.aitu.banksystem.user.util.MessageCode.USER_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final UserConverter converter;


    @Override
    public UserViewResponse findById(Long id) {
        return repository.findById(id).map(converter::convert)
                .orElseThrow(() -> new ServiceValidationException(USER_NOT_FOUND, ErrorCode.RESOURCE_NOT_FOUND));
    }

    @Override
    public Page<UserViewResponse> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(converter::convert);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        User user = repository.findById(id)
                .orElseThrow(() -> new ServiceValidationException(USER_NOT_FOUND, ErrorCode.RESOURCE_NOT_FOUND));
        repository.deleteById(user.getId());

    }
}
