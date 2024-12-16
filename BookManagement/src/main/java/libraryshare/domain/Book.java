package libraryshare.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import libraryshare.BookManagementApplication;
import lombok.Data;

@Entity
@Table(name = "Book_table")
@Data
//<<< DDD / Aggregate Root
public class Book {

    @Id
    private String bookId;

    private String title;

    private String author;

    private String publisher;

    @Enumerated(EnumType.STRING)
    private BookCategory category;

    @Enumerated(EnumType.STRING)
    private BookStatus status;

    @Embedded
    private Isbn isbn;

    @PrePersist
    public void onPrePersist() {}

    public static BookRepository repository() {
        BookRepository bookRepository = BookManagementApplication.applicationContext.getBean(
            BookRepository.class
        );
        return bookRepository;
    }

    //<<< Clean Arch / Port Method
    public void registerBook(RegisterBookCommand registerBookCommand) {
        //implement business logic here:

        BookRegistered bookRegistered = new BookRegistered(this);
        bookRegistered.publishAfterCommit();
    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public void changeBookStatus(
        ChangeBookStatusCommand changeBookStatusCommand
    ) {
        //implement business logic here:

        BookStatusChanged bookStatusChanged = new BookStatusChanged(this);
        bookStatusChanged.publishAfterCommit();
    }

    //>>> Clean Arch / Port Method

    //<<< Clean Arch / Port Method
    public static void loanCreationStatusUpdatePolicy(LoanCreated loanCreated) {
        //implement business logic here:

        /** Example 1:  new item 
        Book book = new Book();
        repository().save(book);

        */

        /** Example 2:  finding and process
        
        repository().findById(loanCreated.get???()).ifPresent(book->{
            
            book // do something
            repository().save(book);


         });
        */

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public static void loanReturnStatusUpdatePolicy(LoanReturned loanReturned) {
        //implement business logic here:

        /** Example 1:  new item 
        Book book = new Book();
        repository().save(book);

        BookStatusChanged bookStatusChanged = new BookStatusChanged(book);
        bookStatusChanged.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        repository().findById(loanReturned.get???()).ifPresent(book->{
            
            book // do something
            repository().save(book);

            BookStatusChanged bookStatusChanged = new BookStatusChanged(book);
            bookStatusChanged.publishAfterCommit();

         });
        */

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
