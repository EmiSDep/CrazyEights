package com.emisdep.actors;

import com.emisdep.Console;
import com.emisdep.crazyEights.Actor;
import com.emisdep.deck.Card;

import java.util.List;

public class Player implements Actor {
    protected final String name;

    public Player(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getAction(Card displayedCard) {
    return Console.getInt("Draw card (1) or Pick a card (2) ", 1, 2, "Invalid");
    }

    @Override
    public int pickCard(List<Card> cardList) {
        cardList.forEach(card -> System.out.print(card.display() + " "));
        System.out.println();
        return Console.getInt("Enter selection 1-" + cardList.size(), 1, cardList.size(), "Invalid entry");
    }
}
