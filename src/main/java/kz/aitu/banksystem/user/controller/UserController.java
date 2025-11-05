package kz.aitu.banksystem.user.controller;

import kz.aitu.banksystem.user.model.dto.UserViewResponse;
import kz.aitu.banksystem.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    @GetMapping("/{id}")
    public UserViewResponse findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping("/get-page")
    public Page<UserViewResponse> findPage(Pageable pageable) {
        return service.findAll(pageable);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
