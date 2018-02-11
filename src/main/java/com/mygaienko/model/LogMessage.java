package com.mygaienko.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.handler.annotation.SendTo;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogMessage {

    private String message;

    @Override
    public String toString() {
        return message;
    }
}
