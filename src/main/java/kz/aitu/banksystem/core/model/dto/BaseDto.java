package kz.aitu.banksystem.core.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class BaseDto {

    private Long id;

    private Date createdAt;

    private Date updatedAt;

    private Date deletedAt;

}
