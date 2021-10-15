package com.emisdep.crazyEights;

import com.emisdep.deck.Card;

import java.util.List;

public interface Actor {

    String getName();
    int getAction(Card displayedCard);
    int pickCard(List<Card> cards);
}
