package com.emisdep.deck;

public abstract class Card {
    protected int rank;
    protected String suit;

    public Card(int rank, String suit){
        this.rank = rank;
        this.suit = suit;
    }

    public int getRank() {
        return rank;
    }

    public String getSuit() {
        return suit;
    }

    public int setRank() {
        return rank;
    }
    public abstract String display();

    public String toString() {
        StringBuilder output = new StringBuilder();
        output.append(rank).append(suit);
        System.out.println(output);
        return new String(output);
    }
}