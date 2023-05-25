package BigGame;

import java.util.Random;

public class House {
    static Byte MIN_SMALL = 3;
    static Byte MAX_SMALL = 10;
    static Byte MIN_BIG = 11;
    static Byte MAX_BIG = 17;



    private int wallet = 0;
    private Dice[] dices;


    public void rollDices() {
        dices = new Dice[3];
        for (int i = 0; i < dices.length; i++) {
            dices[i] = new Dice(0);
            dices[i].roll();
        }
    }
    public void printDices(){
        System.out.println ("The dices are : ");
        for (Dice i :  dices) System.out.println (i.getValue () + "\t");
    }
    public int sumDices(){
        int sum = 0;
        for(Dice i : dices) sum += i.getValue ();
        return sum;
    }

    public Dice[] getDices () {
        return dices;
    }
    public String checkDicesResult(){
        if (sumDices() >= MIN_BIG || sumDices () <= MAX_BIG)
            return "big";
        else if (sumDices () >= MIN_SMALL && sumDices () <= MAX_BIG) {
            return "small";
        } else if (sumDices () == 3 || sumDices () == 18) {
            return "same";
        }
        else {
            return null;
        }

    }
    public int getWallet () {
        return wallet;
    }
    public void addWallet(int amount){
        wallet += amount;
    }
    public void subtractWallet(int amount) {
        if (amount <= wallet) {
            wallet -= amount;
        } else {
            throw new IllegalArgumentException("Insufficient funds in the house wallet.");
        }
    }

}
