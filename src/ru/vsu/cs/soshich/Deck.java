package ru.vsu.cs.soshich;

import java.util.ArrayList;

public class Deck
{
    private ArrayList<String> allCards = new ArrayList<>();

    public void createDeck()
    {
        ArrayList<String> tempArrayList = new ArrayList<>();
        tempArrayList.add("♡");
        tempArrayList.add("♢");
        tempArrayList.add("♤");
        tempArrayList.add("♧");
        for (String i : tempArrayList)
        {
            for (int j = 2; j < 15; j++)
            {
                if (j < 11)
                {
                    allCards.add(i + j);
                }
                else
                {
                    switch (j)
                    {
                        case 11 -> allCards.add(i + "j");
                        case 12 -> allCards.add(i + "q");
                        case 13 -> allCards.add(i + "k");
                        case 14 -> allCards.add(i + "a");
                    }
                }
            }
        }
    }

    public String[][] distributeCards()
    {
        ArrayList<String> cards1 = new ArrayList<>();
        ArrayList<String> cards2 = new ArrayList<>();
        ArrayList<String> cards3 = new ArrayList<>();
        ArrayList<String> cards4 = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 13; j++) {
                int id = (int) (Math.random() * allCards.size());
                switch (i)
                {
                    case 0 -> cards1.add(allCards.get(id));
                    case 1 -> cards2.add(allCards.get(id));
                    case 2 -> cards3.add(allCards.get(id));
                    case 3 -> cards4.add(allCards.get(id));
                }
                allCards.remove(id);
            }
        }
        String[][] matrixForReturn = new String[4][13];
        for (int i = 0; i < 13; i++) {
            matrixForReturn[0][i] = cards1.get(i);
            matrixForReturn[1][i] = cards2.get(i);
            matrixForReturn[2][i] = cards3.get(i);
            matrixForReturn[3][i] = cards4.get(i);
        }
        return matrixForReturn;
    }
}
