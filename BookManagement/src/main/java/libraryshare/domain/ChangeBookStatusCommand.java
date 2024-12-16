package libraryshare.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class ChangeBookStatusCommand {

    private String bookId;
    private BookStatus status;
}
