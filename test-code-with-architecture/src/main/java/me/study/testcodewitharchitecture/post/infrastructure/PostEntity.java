package me.study.testcodewitharchitecture.post.infrastructure;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import me.study.testcodewitharchitecture.user.infrastructure.UserEntity;

@Getter
@Setter
@Entity
@Table(name = "posts")
public class PostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content")
    private String content;

    @Column(name = "created_at")
    private Long createdAt;

    @Column(name = "modified_at")
    private Long modifiedAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity writer;

}
