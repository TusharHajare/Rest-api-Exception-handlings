package com.example.RestapiExceptionhandlings.models;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ApiErrors
{
    String message;
    List<String> details;//error details
    HttpStatus status;//return enum
    LocalDateTime timestamp;
}
