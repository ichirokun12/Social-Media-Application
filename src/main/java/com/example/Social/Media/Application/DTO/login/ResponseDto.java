package com.example.Social.Media.Application.DTO.login;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDto {

    String jwt;
    Long userId;
}
