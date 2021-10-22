package com.Altan.CallService.domain;

import javax.persistence.*;

@Entity(name = "online_users")
@Table
public class OnlineUser {
    @Id
    @SequenceGenerator(name = "online_user_sequence",
            sequenceName = "online_user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "online_user_sequence"
    )
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public OnlineUser() {
    }

    public OnlineUser(Long id, User user) {
        this.id = id;
        this.user = user;
    }

    public OnlineUser(User user) {
        this.user = user;
    }

    public OnlineUser(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "OnlineUser{" +
                "id=" + id +
                ", user=" + user +
                '}';
    }
}
