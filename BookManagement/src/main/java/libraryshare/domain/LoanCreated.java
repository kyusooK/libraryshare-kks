package libraryshare.domain;

import java.util.*;
import libraryshare.domain.*;
import libraryshare.infra.AbstractEvent;
import lombok.*;

@Data
@ToString
public class LoanCreated extends AbstractEvent {

    private Long loanId;
    private Long memberId;
    private Long bookId;
    private Object loanPeriod;
    private Date loanDate;
    private Date dueDate;
    private Object status;
}
