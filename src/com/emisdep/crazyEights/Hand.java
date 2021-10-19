package com.emisdep.crazyEights;

import com.emisdep.deck.Card;
import java.util.ArrayList;
import java.util.List;

public class Hand {
    private List<Card> cards = new ArrayList<>();
    private Actor player;

    public Hand(Actor player) {
        this.player = player;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

     public void displayHand() {
        StringBuilder output = new StringBuilder();
        for(Card card : cards) {
            output.append(card.display()).append(" ");
        }
         System.out.println(output);
     }

    public int getAction(Card displayedCard) {
    return player.getAction(displayedCard);
    }

    public String getName() { return player.getName(); }

    public Card removeCard(int index) {
        return cards.remove(index);
    }

    public int pickCard() {
        return player.pickCard(cards);
    }

    public int handSize() {
        return cards.size();
    }
}
