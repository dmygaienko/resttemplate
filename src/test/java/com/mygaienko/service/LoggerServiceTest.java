package com.mygaienko.service;

import com.mygaienko.model.LogMessage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.test.binder.MessageCollector;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext
public class LoggerServiceTest {

    @Autowired
    private Processor pipe;

    @Autowired
    private MessageCollector messageCollector;

    @Test
    public void enrichLogMessage() {
        pipe.input()
                .send(MessageBuilder.withPayload(new LogMessage("This is my message"))
                        .build());

        Object payload = messageCollector.forChannel(pipe.output())
                .poll()
                .getPayload();

        assertEquals("[1]: This is my message", payload.toString());
    }
}