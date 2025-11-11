package kz.aitu.banksystem.account.repository;

import kz.aitu.banksystem.account.model.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
    List<AccountEntity> findAllActiveAccountsByUserId(Long userId);
}