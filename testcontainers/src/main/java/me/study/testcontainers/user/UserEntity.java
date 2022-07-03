package me.study.testcontainers.user;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Entity
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String username;

    private int age;

    protected UserEntity() {
    }

    @Builder
    public UserEntity(String username, int age) {
        this.username = username;
        this.age = age;
    }
}
