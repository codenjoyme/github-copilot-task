package com.codenjoy.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "visit")
public class Visit {

    public Visit(String fileName, int views) {
        this.fileName = fileName;
        this.views = views;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "views")
    private int views;

    public Visit increaseViews() {
        views++;
        return this;
    }
}
