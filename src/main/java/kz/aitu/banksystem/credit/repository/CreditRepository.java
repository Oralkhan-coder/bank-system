package kz.aitu.banksystem.credit.repository;

import kz.aitu.banksystem.credit.model.entity.CreditEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CreditRepository extends JpaRepository<CreditEntity, Long> {
    List<CreditEntity> findAllByUserId(Long userId);
}
