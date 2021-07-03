package jpabook.start;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;  //**

/**
 * User: HolyEyE
 * Date: 13. 5. 24. Time: 오후 7:43
 */
@Getter
@Setter
@Entity // 이 클래스를 테이블과 매핑한다고 JPA에게 알려줌.
@Table(name="MEMBER") // 이 테이블의 이름은 "MEMBER"이다.
public class Member {

    @Id // Primary Key
    @Column(name = "ID") // 컬럼명 = ID
    private String id;

    @Column(name = "NAME") // 컬럼명 = NAME
    private String username;

    private Integer age; // 컬럼명 = age
}
