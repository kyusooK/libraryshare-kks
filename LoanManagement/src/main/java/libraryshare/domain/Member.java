package libraryshare.domain;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//<<< DDD / Value Object
@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Member {

    private Long MemberId;

    private String Name;

    private String PhoneNumber;


}
//>>> DDD / Value Object
