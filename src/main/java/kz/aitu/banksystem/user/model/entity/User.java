package kz.aitu.banksystem.user.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import kz.aitu.banksystem.core.model.entity.AuditModel;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.stereotype.Indexed;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "update users set deleted_at = now() where id = ?")
@Where(clause = "deleted_at is null")
@SequenceGenerator(
        name = "seq",
        sequenceName = "s_users",
        allocationSize = 1
)
@EqualsAndHashCode(callSuper = true)
public class User extends AuditModel {

    @Column(name = "email", unique = true)
    @Email
    private String email;

    @Column(name = "phone_number", unique = true, nullable = false)
    private String phoneNumber;

    private String role;

    private String keyCloakUserId;

    private String username;

    private String firstName;

    private String lastName;

    private String middleName;
}
