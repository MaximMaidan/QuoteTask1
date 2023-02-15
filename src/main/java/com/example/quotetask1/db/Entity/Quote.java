package com.example.quotetask1.db.Entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "quote_t")
public class Quote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "quoteContent", nullable = false, length = 5000)
    private String quoteContent;

    @Column(name = "rating")
    private Integer rating = 0;

    @Column (name = "quoteDate", nullable = false)
    private LocalDateTime quoteDate;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn
    private User user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQuoteContent() {
        return quoteContent;
    }

    public void setQuoteContent(String quoteContent) {
        this.quoteContent = quoteContent;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public LocalDateTime getQuoteDate() {
        return quoteDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @PrePersist
    private void init(){
        quoteDate = LocalDateTime.now();
    }

}
