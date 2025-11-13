package kz.aitu.banksystem.credit.model.entity;

import jakarta.persistence.*;
import kz.aitu.banksystem.core.model.entity.AuditModel;
import kz.aitu.banksystem.credit.model.CreditStatus;
import kz.aitu.banksystem.credit.model.CreditType;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "credits")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "update credits set deleted_at = now() where id = ?")
@Where(clause = "deleted_at is null")
@SequenceGenerator(
        name = "seq",
        sequenceName = "s_credits",
        allocationSize = 1
)
public class CreditEntity extends AuditModel {

    @Enumerated(EnumType.STRING)
    @Column(name = "credit_type", nullable = false)
    private CreditType creditType;

    @Column(name = "amount", nullable = false)
    private Double amount;

    @Column(name = "interest_rate", nullable = false)
    private Double interestRate;

    @Column(name = "term_months", nullable = false)
    private Integer termMonths;

    @Column(name = "remaining_amount", nullable = false)
    private Double remainingAmount;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private CreditStatus status;

    @Column(name = "user_id", nullable = false)
    private Long userId;
}

