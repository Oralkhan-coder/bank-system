package kz.aitu.banksystem.core.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

import java.io.Serializable;

@Data
@MappedSuperclass
public class IDSequenced implements Serializable {

    @Id
    @GeneratedValue(generator = "seq")
    @Column(updatable = false, nullable = false)
    private Long id;

}
