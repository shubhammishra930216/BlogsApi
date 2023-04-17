package com.shubhammishra.blogsapi.entiities;

import com.fasterxml.jackson.annotation.JsonView;
import com.shubhammishra.blogsapi.view.View;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="post_id")
    private Long postId;

    @Column(name="post_title",nullable = false)
    private String title;

    private String content;

    @Column(name = "image_name")
    private String imageName;

    @Column(name="created_at",nullable = false)
    private Date createdAt;

    @Column(name="updated_at",nullable = false)
    private Date updatedAt;

    @ManyToOne
    private Category category;

    @ManyToOne
    private User user;


}
