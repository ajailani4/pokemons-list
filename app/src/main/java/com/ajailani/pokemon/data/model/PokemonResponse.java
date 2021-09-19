package com.ajailani.pokemon.data.model;

import java.util.ArrayList;

public class PokemonResponse {
    private Integer count;
    private String previous, next;
    private ArrayList<Pokemon> results;

    public PokemonResponse(
        Integer count,
        String previous,
        String next,
        ArrayList<Pokemon> results
    ) {
        this.count = count;
        this.previous = previous;
        this.next = next;
        this.results = results;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public ArrayList<Pokemon> getResults() {
        return results;
    }

    public void setResults(ArrayList<Pokemon> results) {
        this.results = results;
    }
}
