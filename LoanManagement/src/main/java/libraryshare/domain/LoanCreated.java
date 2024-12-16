package libraryshare.domain;

import java.time.LocalDate;
import java.util.*;
import libraryshare.domain.*;
import libraryshare.infra.AbstractEvent;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class LoanCreated extends AbstractEvent {

    private Long loanId;
    private Long memberId;
    private Long bookId;
    private LoanPeriod loanPeriod;
    private Date loanDate;
    private Date dueDate;
    private LoanStatus status;

    public LoanCreated(Loan aggregate) {
        super(aggregate);
    }

    public LoanCreated() {
        super();
    }
}
//>>> DDD / Domain Event
