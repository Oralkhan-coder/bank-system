package kz.aitu.banksystem.otp.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import kz.aitu.banksystem.core.model.entity.AuditModel;
import lombok.*;

@Entity
@Table(name = "code")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@SequenceGenerator(
        name = "seq",
        sequenceName = "s_codes",
        initialValue = 1,
        allocationSize = 1
)
public class Code extends AuditModel {
    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "receiver", nullable = false)
    private String receiver;

    @Column(name = "sent_type")
    private Integer sentType;
}
