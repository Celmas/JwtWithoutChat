package ru.itis.longpolling.dto;

import lombok.Data;
import ru.itis.longpolling.model.Role;

import java.util.List;

@Data
public class RegisterDto {
    private String login;
    private String email;
    private String password;
    List<Role> roles;
}
