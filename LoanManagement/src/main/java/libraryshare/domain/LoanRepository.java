package libraryshare.domain;

import libraryshare.domain.*;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//<<< PoEAA / Repository
@RepositoryRestResource(collectionResourceRel = "loans", path = "loans")
public interface LoanRepository
    extends PagingAndSortingRepository<Loan, Long> {}
