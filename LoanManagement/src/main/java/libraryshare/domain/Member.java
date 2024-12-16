package libraryshare.domain;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

//<<< DDD / Value Object
@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Member {

    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long MemberId;

    @GeneratedValue(strategy = GenerationType.AUTO)
    private String Name;

    @GeneratedValue(strategy = GenerationType.AUTO)
    private String PhoneNumber;

    @GeneratedValue(strategy = GenerationType.AUTO)
    private Email Email;
}
//>>> DDD / Value Object
