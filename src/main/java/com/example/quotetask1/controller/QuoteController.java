package com.example.quotetask1.controller;

import com.example.quotetask1.db.Entity.Quote;
import com.example.quotetask1.service.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@RestController

public class QuoteController {

    @Autowired
    private QuoteService quoteService;

    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }

    @PostMapping("/quotes/save")
    public Quote saveQuote(@RequestBody Quote quote, Principal principal) {
        return quoteService.saveQuote(principal, quote);
    }


    @GetMapping("/quotes/delete/{id}")
    public String deleteQuote(@PathVariable("id") Integer id) {
        quoteService.deleteQuoteById(id);
        return "quote deleted";
    }

    @GetMapping("/quotes/details/{id}")
    public Optional<Quote> showQuote(@PathVariable("id") Integer id) {
        return quoteService.findById(id);
    }

    @GetMapping("/quotes")
    public List<Quote> showAllQuotes() {
        return quoteService.listAll();
    }

    @GetMapping("/quotes/top10")
    public List<Quote> showTop10Quotes() {
        return quoteService.listAll().stream()
                .sorted(Collections.reverseOrder(Comparator.comparingInt(Quote::getRating)))
                .limit(10)
                .toList();
    }

    @GetMapping("/quotes/flop10")
    public List<Quote> showFlop10Quotes() {
        return quoteService.listAll().stream()
                .sorted(Comparator.comparingInt(Quote::getRating))
                .limit(10)
                .toList();
    }

    @GetMapping("/quotes/last")
    public List<Quote> showLastQuotes() {
        return quoteService.listAll().stream()
                .sorted(Collections.reverseOrder(Comparator.comparing(Quote::getQuoteDate)))
                .toList();
    }

    @GetMapping("/quotes/random")
    public Optional<Quote> showRandomQuote() {
        List<Quote> listQuote = quoteService.listAll();
        return Optional.of(listQuote.stream()
                .sorted((o1, o2) -> ThreadLocalRandom.current().nextInt(-1, 2))
                .findAny()
                .get());
    }

    @PostMapping("/quotes/like/{id}")
    public void increaseRating(@PathVariable("id") Integer id){
        quoteService.addRating(id);
    }

    @PostMapping("/quotes/dislike/{id}")
    public void decreaseRating(@PathVariable("id") Integer id, Principal principal){
                quoteService.removeRating(id);
    }

    @PostMapping("/quotes/update/{id}")
    public Quote updateQuote(@PathVariable("id") Integer id,
                             @RequestBody Quote quote2,
                             Principal principal) {
        return quoteService.updateQuote(id, quote2, principal);
    }

}
