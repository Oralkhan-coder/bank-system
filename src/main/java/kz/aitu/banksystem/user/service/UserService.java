package kz.aitu.banksystem.user.service;

import kz.aitu.banksystem.registration.model.dto.request.RegistrationRequest;
import kz.aitu.banksystem.user.model.dto.UserViewResponse;
import kz.aitu.banksystem.user.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserService {
    UserViewResponse findById(Long id);
    Page<UserViewResponse> findAll(Pageable pageable);
    Optional<User> findByPhoneNumber(String phoneNumber);
    void deleteById(Long id);
    Long save(RegistrationRequest request, String userId, String role);
}
