package libraryshare.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class CreateLoanCommand {

    private Long memberId;
    private Long bookId;
    private LoanPeriod loanPeriod;
    private Date loanDate;
    private Date dueDate;
    private Long loanId;
    private Member member;
    private BookReference bookReference;
    private LoanStatus loanStatus;
}
