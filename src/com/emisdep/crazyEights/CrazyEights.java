package com.emisdep.crazyEights;

import com.emisdep.Console;
import com.emisdep.actors.Player;
import com.emisdep.deck.Card;
import com.emisdep.deck.Deck;
import com.emisdep.deck.Ranks;
import com.emisdep.deck.StandardDeck;

import java.util.ArrayList;
import java.util.List;

public class CrazyEights {
    List<Hand> hands = new ArrayList<>();
    List<Card> discard = new ArrayList<>();
    Deck deck;

    public CrazyEights () {
        addPlayer();

    }

    public void play() {
        deck = new StandardDeck();
        deck.shuffle();
       // discardPile.add(deck.deal());
        discard.add(deck.draw());
        for (int count = 0; count < 5; count++) {
            hands.forEach(hand -> hand.addCard(deck.draw()));
        }
        while (true) {
            turn();
        }
    }

    public void addPlayer() {
        int playerCount = Console.getInt("How many players?", 1, 6, "invalid player selection");
        for (int count = 0; count < playerCount; count++) {
            hands.add(new Hand(
                    new Player(Console.getString("Enter Name", true)))
            );

        }
    }

    private void turn() {
        for (Hand player : hands) {
            System.out.println(player.getName());
            player.displayHand();
            int action = player.getAction(discard.get(discard.size() -  1));
            switch (action) {
                case 1 -> drawCard(player);
                case 2 -> discardCard(player);
            }
        }
        System.out.println(discard.get(discard.size() -  1).display());
    }

    public void drawCard(Hand player) {
        player.addCard(deck.draw());
    }

    public void discardCard(Hand player) {
        int index = player.pickCard() - 1;
        Card removedCard = player.removeCard(index);
        Card activeCard = discard.get(discard.size() -  1);
        if (removedCard.getSuit().equals(activeCard.getSuit()) || removedCard.getRank() == activeCard.getRank()) {
            discard.add(removedCard);
        } else {
            System.out.println("Invalid");
            player.addCard(removedCard);
        }
    }
}
