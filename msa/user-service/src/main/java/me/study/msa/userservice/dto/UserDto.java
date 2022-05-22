package me.study.msa.userservice.dto;

import java.time.LocalDate;
import lombok.Data;

@Data
public class UserDto {

    private String email;
    private String name;
    private String pwd;
    private String userId;
    private LocalDate createdAt;

    private String encryptedPwd;
}
