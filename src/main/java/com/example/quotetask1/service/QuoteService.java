package com.example.quotetask1.service;

import com.example.quotetask1.db.Entity.Quote;
import com.example.quotetask1.db.Entity.User;
import com.example.quotetask1.db.Repository.QuoteRepository;
import com.example.quotetask1.db.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
public class QuoteService {

    @Autowired
    private QuoteRepository quoteRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Quote> listAll() {
        return (List<Quote>) quoteRepository.findAll();
    }

    public Quote saveQuote(Principal principal, Quote quote) {
        quote.setUser(getUserByPrincipal(principal));
        return quoteRepository.save(quote);
    }

    public User getUserByPrincipal(Principal principal) {
        if (principal == null)
            return new User();
        return userRepository.findByUsername(principal.getName());
    }

    public void deleteQuoteById(Integer id) {
        Optional<Quote> deleted = quoteRepository.findById(id);
        deleted.ifPresent(quote -> quoteRepository.delete(quote));
    }

    public Optional<Quote> findById(Integer id) {
        return quoteRepository.findById(id);
    }

    public void addRating(Integer id) {
        Optional<Quote> quote = quoteRepository.findById(id);
        Quote quote1 = new Quote();
        quote1.setQuoteContent(quote.get().getQuoteContent());
        quote1.setRating((quote.get().getRating()) + 1);
        quote1.setUser(quote.get().getUser());
        deleteQuoteById(id);
        quoteRepository.save(quote1);

    }

    public void removeRating(Integer id) {
        Optional<Quote> quote = quoteRepository.findById(id);
        Quote quote1 = new Quote();
        quote1.setQuoteContent(quote.get().getQuoteContent());
        quote1.setRating((quote.get().getRating()) - 1);
        quote1.setUser(quote.get().getUser());
        deleteQuoteById(id);
        quoteRepository.save(quote1);
    }

    public Quote updateQuote(Integer id, Quote quote2, Principal principal) {
        Optional<Quote> quote = quoteRepository.findById(id);
        Quote quote1 = new Quote();
        quote1.setQuoteContent(quote2.getQuoteContent());
        quote1.setRating(quote.get().getRating());
        quote1.setUser(getUserByPrincipal(principal));
        deleteQuoteById(id);
        return quoteRepository.save(quote1);
    }

}
