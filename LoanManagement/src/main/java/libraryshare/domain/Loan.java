package libraryshare.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import libraryshare.LoanManagementApplication;
import libraryshare.domain.LoanReturned;
import lombok.Data;

@Entity
@Table(name = "Loan_table")
@Data
//<<< DDD / Aggregate Root
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long loanId;

    @Embedded
    private Member member;

    @Embedded
    private BookReference bookReference;

    @Embedded
    private LoanPeriod loanPeriod;

    @Enumerated(EnumType.STRING)
    private LoanStatus loanStatus;

    private Date loanDate;

    private Date dueDate;

    @PostPersist
    public void onPostPersist() {
        LoanReturned loanReturned = new LoanReturned(this);
        loanReturned.publishAfterCommit();
    }

    @PrePersist
    public void onPrePersist() {}

    public static LoanRepository repository() {
        LoanRepository loanRepository = LoanManagementApplication.applicationContext.getBean(
            LoanRepository.class
        );
        return loanRepository;
    }

    //<<< Clean Arch / Port Method
    public void createLoan(CreateLoanCommand createLoanCommand) {
        //implement business logic here:

        LoanCreated loanCreated = new LoanCreated(this);
        loanCreated.publishAfterCommit();
    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
