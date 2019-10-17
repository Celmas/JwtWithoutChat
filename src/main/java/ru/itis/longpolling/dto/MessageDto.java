package ru.itis.longpolling.dto;

import lombok.Data;

@Data
public class MessageDto {
    private String tokenValue;
    private String text;
}
