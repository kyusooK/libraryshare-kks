package libraryshare.domain;

import java.util.*;
import libraryshare.domain.*;
import libraryshare.infra.AbstractEvent;
import lombok.*;

@Data
@ToString
public class LoanReturned extends AbstractEvent {

    private Long loanId;
    private Long bookId;
    private Date returnDate;
    private Object status;
}
