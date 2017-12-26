package org.academiadecodigo.bootcamp;

public class Player {

    private String name;
    private int victories = 0;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getVictories() {
        return victories;
    }


    public void win() {
        victories++;
    }

    public GameHand chooseHand() {
        return GameHand.values()[(int) (Math.random() * GameHand.values().length)];
    }
}
