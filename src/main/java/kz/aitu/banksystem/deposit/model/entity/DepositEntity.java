package kz.aitu.banksystem.deposit.model.entity;

import jakarta.persistence.*;
import kz.aitu.banksystem.deposit.model.DepositType;
import lombok.*;

@Entity
@Table(name = "deposits")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DepositEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DepositType type;

    @Column(name = "initial_amount", nullable = false)
    private Double initialAmount;

    @Column(name = "interest_rate", nullable = false)
    private Double interestRate;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "duration_months", nullable = false)
    private Integer durationMonths;
}