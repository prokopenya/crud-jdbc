package com.github.prokopenya.model;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import java.util.Date;

@Data
public class Person {
    @Id
    //@GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @NonNull
    private String name;

    @NonNull
    private String phone;

    private Date createdAt;

    public Person(long id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
    }

    @PrePersist
    void createdAt() {
        this.createdAt = new Date();
    }
}
