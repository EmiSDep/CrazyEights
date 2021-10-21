package com.emisdep.actors;

import com.emisdep.crazyEights.Actor;
import com.emisdep.deck.Card;

import java.util.List;

public class ComputerPlayer implements Actor {

    @Override
    public String getName() {
        return "CPU";
    }

    @Override
    public int getAction(Card displayedCard) {
        return 0;
    }

    @Override
    public int pickCard(List<Card> cards) {
        return 0;
    }
}
