package kz.aitu.banksystem.account.model.entity;

import jakarta.persistence.*;
import kz.aitu.banksystem.core.model.entity.AuditModel;
import lombok.*;
import org.hibernate.annotations.DialectOverride;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "accounts")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "update accounts set deleted_at = now() where id = ?")
@Where(clause = "deleted_at is null")
@SequenceGenerator(
        name = "seq",
        sequenceName = "s_accounts",
        allocationSize = 1
)
public class AccountEntity extends AuditModel {

    @Column(name = "account_number", unique = true, nullable = false)
    private String accountNumber;

    @Column(name = "balance", nullable = false)
    private Double balance;

    @Column(name = "currency", nullable = false)
    private String currency;

    @Column(name = "user_id", nullable = false)
    private Long userId;
}