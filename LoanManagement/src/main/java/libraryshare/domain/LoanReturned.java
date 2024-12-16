package libraryshare.domain;

import java.time.LocalDate;
import java.util.*;
import libraryshare.domain.*;
import libraryshare.infra.AbstractEvent;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class LoanReturned extends AbstractEvent {

    private Long loanId;
    private Long bookId;
    private Date returnDate;
    private LoanStatus status;

    public LoanReturned(Loan aggregate) {
        super(aggregate);
    }

    public LoanReturned() {
        super();
    }
}
//>>> DDD / Domain Event
