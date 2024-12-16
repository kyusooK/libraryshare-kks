package libraryshare.infra;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.naming.NameParser;
import javax.naming.NameParser;
import javax.transaction.Transactional;
import libraryshare.config.kafka.KafkaProcessor;
import libraryshare.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

//<<< Clean Arch / Inbound Adaptor
@Service
@Transactional
public class PolicyHandler {

    @Autowired
    BookRepository bookRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='LoanCreated'"
    )
    public void wheneverLoanCreated_LoanCreationStatusUpdatePolicy(
        @Payload LoanCreated loanCreated
    ) {
        LoanCreated event = loanCreated;
        System.out.println(
            "\n\n##### listener LoanCreationStatusUpdatePolicy : " +
            loanCreated +
            "\n\n"
        );

        // Comments //
        //대출 생성 시 도서 상태를 '대출중'으로 변경

        // Sample Logic //
        Book.loanCreationStatusUpdatePolicy(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='LoanReturned'"
    )
    public void wheneverLoanReturned_LoanReturnStatusUpdatePolicy(
        @Payload LoanReturned loanReturned
    ) {
        LoanReturned event = loanReturned;
        System.out.println(
            "\n\n##### listener LoanReturnStatusUpdatePolicy : " +
            loanReturned +
            "\n\n"
        );

        // Comments //
        //반납 시 도서 상태를 '대출가능'으로 복구

        // Sample Logic //
        Book.loanReturnStatusUpdatePolicy(event);
    }
}
//>>> Clean Arch / Inbound Adaptor
