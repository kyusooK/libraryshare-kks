package libraryshare.domain;

import java.time.LocalDate;
import java.util.*;
import libraryshare.domain.*;
import libraryshare.infra.AbstractEvent;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class BookStatusChanged extends AbstractEvent {

    private String bookId;
    private BookStatus status;

    public BookStatusChanged(Book aggregate) {
        super(aggregate);
    }

    public BookStatusChanged() {
        super();
    }
}
//>>> DDD / Domain Event
