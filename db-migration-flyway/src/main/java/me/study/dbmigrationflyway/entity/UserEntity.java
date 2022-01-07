package me.study.dbmigrationflyway.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tbl_user")
@AttributeOverride(name = "id", column = @Column(name = "user_id"))
@Where(clause = "deleted=0")
@Entity
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private double gpa;

    private int age;

    @Builder
    public UserEntity(String name, double gpa, int age) {
        this.name = name;
        this.gpa = gpa;
        this.age = age;
    }
}
