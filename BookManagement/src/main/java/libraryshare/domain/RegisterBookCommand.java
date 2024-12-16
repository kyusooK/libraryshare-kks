package libraryshare.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class RegisterBookCommand {

    private String title;
    private String author;
    private String publisher;
    private BookCategory category;
    private String bookId;
    private Isbn isbn;
    private BookStatus status;
}
