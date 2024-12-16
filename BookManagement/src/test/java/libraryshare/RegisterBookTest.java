package libraryshare;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import libraryshare.domain.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.verifier.messaging.MessageVerifier;
import org.springframework.cloud.contract.verifier.messaging.boot.AutoConfigureMessageVerifier;
import org.springframework.cloud.contract.verifier.messaging.internal.ContractVerifierMessage;
import org.springframework.cloud.contract.verifier.messaging.internal.ContractVerifierMessaging;
import org.springframework.cloud.contract.verifier.messaging.internal.ContractVerifierObjectMapper;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.test.binder.MessageCollector;
import org.springframework.context.ApplicationContext;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.MimeTypeUtils;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMessageVerifier
public class RegisterBookTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(
        RegisterBookTest.class
    );

    @Autowired
    private MessageCollector messageCollector;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private MessageVerifier<Message<?>> messageVerifier;

    @Autowired
    public BookRepository repository;

    @Test
    @SuppressWarnings("unchecked")
    public void test0() {
        //given:
        Book entity = new Book();

        entity.setBookId(null);
        entity.setTitle(null);
        entity.setIsbn(null);
        entity.setAuthor(null);
        entity.setPublisher(null);
        entity.setCategory(null);
        entity.setStatus("N/A");

        repository.save(entity);

        //when:
        try {
            RegisterBookCommand command = new RegisterBookCommand();

            command.setBookId("N/A");
            command.setTitle("자바의 정석");
            command.setIsbn("9788994492032");
            command.setAuthor("남궁성");
            command.setPublisher("도우출판");
            command.setCategory("ACADEMIC");
            command.setStatus("N/A");

            entity.registerBook(command);

            //then:
            this.messageVerifier.send(
                    MessageBuilder
                        .withPayload(entity)
                        .setHeader(
                            MessageHeaders.CONTENT_TYPE,
                            MimeTypeUtils.APPLICATION_JSON
                        )
                        .build(),
                    "libraryshare"
                );

            Message<?> receivedMessage =
                this.messageVerifier.receive(
                        "libraryshare",
                        5000,
                        TimeUnit.MILLISECONDS
                    );
            assertNotNull("Resulted event must be published", receivedMessage);

            String receivedPayload = (String) receivedMessage.getPayload();

            BookRegistered outputEvent = objectMapper.readValue(
                receivedPayload,
                BookRegistered.class
            );

            LOGGER.info("Response received: {}", outputEvent);

            assertEquals(outputEvent.getBookId(), "BOOK-001");
            assertEquals(outputEvent.getTitle(), "자바의 정석");
            assertEquals(outputEvent.getIsbn(), "9788994492032");
            assertEquals(outputEvent.getAuthor(), "남궁성");
            assertEquals(outputEvent.getPublisher(), "도우출판");
            assertEquals(outputEvent.getCategory(), "ACADEMIC");
            assertEquals(outputEvent.getStatus(), "AVAILABLE");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            assertTrue(e.getMessage(), false);
        }
    }
}
