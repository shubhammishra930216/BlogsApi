package com.shubhammishra.blogsapi.entiities;

import com.fasterxml.jackson.annotation.JsonView;
import com.shubhammishra.blogsapi.view.View;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "posts")
public class Post extends BaseEntity implements Serializable {

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

    @OneToMany(mappedBy = "post",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Comments> comments = new ArrayList<>();



}
