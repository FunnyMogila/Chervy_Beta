package ru.vsu.cs.soshich;

import java.util.ArrayList;

public class Gamer
{
    ArrayList<String> cards = new ArrayList<>();

    public ArrayList<String> getCards() {
        return cards;
    }

    public void setCards(ArrayList<String> cards) {
        this.cards = cards;
    }

    public void changeCardByIndex(int i, String newName) {
        cards.set(i,newName);
    }
}
