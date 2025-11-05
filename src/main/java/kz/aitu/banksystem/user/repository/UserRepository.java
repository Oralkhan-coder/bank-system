package kz.aitu.banksystem.user.repository;

import kz.aitu.banksystem.user.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
