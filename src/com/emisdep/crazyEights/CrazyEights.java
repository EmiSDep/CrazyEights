package com.emisdep.crazyEights;

import com.emisdep.Console;
import com.emisdep.actors.Player;
import com.emisdep.deck.Card;
import com.emisdep.deck.Deck;
import com.emisdep.deck.Ranks;
import com.emisdep.deck.StandardDeck;

import java.util.ArrayList;
import java.util.Arrays;
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
            System.out.println("Card in play : " + discard.get(discard.size() - 1).display());
            turn();
        }
    }

    public void addPlayer() {
        int playerCount = Console.getInt("How many players?", 1, 5, "invalid player selection");
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
                case 2 -> validateCard(player);
            }

            validateWinner(player);
        }
        System.out.println(discard.get(discard.size() -  1).display());
    }

    public void drawCard(Hand player) {
        player.addCard(deck.draw());
    }

    public void discardCard(Card removeCard) {
        discard.add(removeCard);
    }

    public void validateCard(Hand player) {
        int index = player.pickCard() - 1;
        Card removedCard = player.removeCard(index);
        Card activeCard = discard.get(discard.size() -  1);
        if (removedCard.getSuit().equals(activeCard.getSuit()) || removedCard.getRank() == activeCard.getRank()) {
            discard.add(removedCard);
            if (removedCard.getRank() == 8) {
                int chooseSuit = Console.getInt("Select next suit: " + Arrays.toString(StandardDeck.SUITS), 1, StandardDeck.SUITS.length, "Invalid");
                discardCard(new Ranks(8, StandardDeck.SUITS[chooseSuit - 1]));
            } else {
                discardCard(removedCard);
            }
        } else {
            //System.out.println("Invalid");
            player.addCard(removedCard);
            if(Console.getInt("Invalid play, draw a card? (1) Yes (2) No ", 1, 2, "Invalid Selection") == 1) {
                drawCard(player);
            } else {
                validateCard(player);
            }
        }
    }

//    public void validate8(Hand player) {
//        int index = player.pickCard() - 1;
//        Card removedCard = player.removeCard(index);
////        Card activeCard = discard.get(discard.size() -  1);
//            if (removedCard.getRank() == 8) {
//                int chooseSuit = Console.getInt("Select next suit: " + Arrays.toString(StandardDeck.SUITS), 1, StandardDeck.SUITS.length, "Invalid");
//                discardCard(new Ranks(8, StandardDeck.SUITS[chooseSuit - 1]));
//            } else {
//                discardCard(removedCard);
//            }
//                player.addCard(removedCard);
//        }




    public void validateWinner(Hand player) {
        if (player.handSize() == 0) {
            System.out.println(player.getName() + " Wins");
            System.exit(0);
        }
    }


}
