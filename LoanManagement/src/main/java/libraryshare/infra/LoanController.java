package libraryshare.infra;

import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import libraryshare.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//<<< Clean Arch / Inbound Adaptor

@RestController
// @RequestMapping(value="/loans")
@Transactional
public class LoanController {

    @Autowired
    LoanRepository loanRepository;

    @RequestMapping(
        value = "/loans/createloan",
        method = RequestMethod.POST,
        produces = "application/json;charset=UTF-8"
    )
    public Loan createLoan(
        HttpServletRequest request,
        HttpServletResponse response,
        @RequestBody CreateLoanCommand createLoanCommand
    ) throws Exception {
        System.out.println("##### /loan/createLoan  called #####");
        Loan loan = new Loan();
        loan.createLoan(createLoanCommand);
        loanRepository.save(loan);
        return loan;
    }
}
//>>> Clean Arch / Inbound Adaptor
