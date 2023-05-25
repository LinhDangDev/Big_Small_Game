package BigGame;

import java.util.Random;

public class Dice {
    private int value;
    public Dice (int diceValue){

    }
    //getter and setter
    public int getValue () {
        return value;
    }

    public void setValue (int value) {
        this.value = value;
    }

    //Random xuc sac co 6 nut de ra duoc value can test
    public void roll (){
        Random random = new Random();
        this.value = random.nextInt(6) + 1;
    }
}
