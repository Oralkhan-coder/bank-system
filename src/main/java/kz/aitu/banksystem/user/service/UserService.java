package kz.aitu.banksystem.user.service;

import kz.aitu.banksystem.user.model.dto.UserViewResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    UserViewResponse findById(Long id);
    Page<UserViewResponse> findAll(Pageable pageable);
    void deleteById(Long id);
}
