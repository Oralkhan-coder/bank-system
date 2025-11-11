package kz.aitu.banksystem.otp.repository;

import kz.aitu.banksystem.otp.model.entity.Code;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CodeRepository extends JpaRepository<Code, Long> {
    Optional<Code> findByDeletedAtIsNullAndReceiverOrderByIdDesc(String phoneNumber);
}
