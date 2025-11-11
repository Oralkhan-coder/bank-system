package kz.aitu.banksystem.deposit.repository;

import kz.aitu.banksystem.deposit.model.entity.DepositEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepositRepository extends JpaRepository<DepositEntity, Long> {
    List<DepositEntity> findAllByUserId(Long userId);
}