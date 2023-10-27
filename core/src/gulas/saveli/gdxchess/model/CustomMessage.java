package model;

import lombok.Data;

@Data
public class CustomMessage {
    private String message;
    private RuntimeException exception;
}
