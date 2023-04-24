package com.shubhammishra.blogsapi.entiities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "comments")
@Entity
public class Comments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="comment_id")
    private Long commentId;

    @Column(length = 1000)
    private String content;

    @ManyToOne
    private Post post;

    @ManyToOne
    private User user;
}
