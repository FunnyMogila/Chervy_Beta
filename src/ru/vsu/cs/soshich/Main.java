package ru.vsu.cs.soshich;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class Main
{
    private static final int END_SCORE = 100;

    private static int stage = 0;
    private static final Deck deck = new Deck();
    private static final ArrayList<String> cardsOnTable = new ArrayList<>();
    private static boolean isHeartsAvailable = false;
    private static final Gamer g1 = new Gamer();
    private static final Gamer g2 = new Gamer();
    private static final Gamer g3 = new Gamer();
    private static final Gamer g4 = new Gamer();
    private static int score1 = 0;
    private static int score2 = 0;
    private static int score3 = 0;
    private static int score4 = 0;

    public static void main(String[] args)
    {
        while (score1 < END_SCORE && score2 < END_SCORE && score3 < END_SCORE && score4 < END_SCORE) {
            deck.createDeck();
            String[][] tempMatrix = deck.distributeCards();
            g1.setCards(new ArrayList<>(List.of(tempMatrix[0])));
            g2.setCards(new ArrayList<>(List.of(tempMatrix[1])));
            g3.setCards(new ArrayList<>(List.of(tempMatrix[2])));
            g4.setCards(new ArrayList<>(List.of(tempMatrix[3])));
            swapCards();
            while (!g4.getCards().isEmpty())
            {
                int id1 = (int) (Math.random() * g1.getCards().size());
                if (!isHeartsAvailable) {
                    while (g1.getCards().get(id1).charAt(0) == '♡') {
                        id1 = (int) (Math.random() * g1.getCards().size());
                    }
                }
            }
            System.out.println(g1.getCards());
        }
    }

    private static void swapCards()
    {
        int tempConst = 0;
        switch (stage)
        {
            case 0 -> tempConst = 1;
            case 1 -> tempConst = -1;
            case 2 -> tempConst = 2;
        }
        stage++;
        if (stage == 4)
        {
            stage = 0;
        }
        else
        {
            ArrayList<Integer> indexes1 = new ArrayList<>();
            ArrayList<Integer> indexes2 = new ArrayList<>();
            ArrayList<Integer> indexes3 = new ArrayList<>();
            ArrayList<Integer> indexes4 = new ArrayList<>();
            for (int i = 0; i < 4; i++)
            {
                ArrayList<Integer> tempIndexes = new ArrayList<>();
                Random r = new Random();
                while (tempIndexes.size() < 3)
                {
                    int random = r.nextInt(13);
                    if (!tempIndexes.contains(random))
                    {
                        tempIndexes.add(random);
                    }
                }
                switch (i)
                {
                    case 0 -> indexes1 = tempIndexes;
                    case 1 -> indexes2 = tempIndexes;
                    case 2 -> indexes3 = tempIndexes;
                    case 3 -> indexes4 = tempIndexes;
                }
            }
            ArrayList<String> cards1 = new ArrayList<>();
            ArrayList<String> cards2 = new ArrayList<>();
            ArrayList<String> cards3 = new ArrayList<>();
            ArrayList<String> cards4 = new ArrayList<>();
            for (int i = 0; i < 3; i++)
            {
                cards1.add(g1.getCards().get(indexes1.get(i)));
                cards2.add(g2.getCards().get(indexes2.get(i)));
                cards3.add(g3.getCards().get(indexes3.get(i)));
                cards4.add(g4.getCards().get(indexes4.get(i)));
            }
            for (int i = 0; i < 3; i++)
            {
                g1.changeCardByIndex(indexes1.get(i), "");
                g2.changeCardByIndex(indexes2.get(i), "");
                g3.changeCardByIndex(indexes3.get(i), "");
                g4.changeCardByIndex(indexes4.get(i), "");
            }
            if (tempConst == 1)
            {
                ArrayList<String> tempCards = cards1;
                cards1 = cards4;
                cards4 = cards3;
                cards3 = cards2;
                cards2 = tempCards;
            }
            else if (tempConst == -1)
            {
                ArrayList<String> tempCards = cards1;
                cards1 = cards2;
                cards2 = cards3;
                cards3 = cards4;
                cards4 = tempCards;
            }
            else if (tempConst == 2)
            {
                ArrayList<String> tempCards = cards1;
                cards1 = cards3;
                cards3 = tempCards;
                tempCards = cards2;
                cards2 = cards4;
                cards4 = tempCards;
            }
            for (int i = 0; i < 13; i++)
            {
                if (Objects.equals(g1.getCards().get(i), ""))
                {
                    g1.changeCardByIndex(i, cards1.get(0));
                    cards1.remove(0);
                }
                if (Objects.equals(g2.getCards().get(i), ""))
                {
                    g2.changeCardByIndex(i, cards2.get(0));
                    cards2.remove(0);
                }
                if (Objects.equals(g3.getCards().get(i), ""))
                {
                    g3.changeCardByIndex(i, cards3.get(0));
                    cards3.remove(0);
                }
                if (Objects.equals(g4.getCards().get(i), ""))
                {
                    g4.changeCardByIndex(i, cards4.get(0));
                    cards4.remove(0);
                }
            }
        }
    }
}
