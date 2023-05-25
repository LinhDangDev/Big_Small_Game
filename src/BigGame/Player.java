package BigGame;

public class Player {
    private String choice;
    private int bet;
    private int wallet;
    public String getChoice () {
        return choice;
    }

    public void setChoice (String choice) {
        this.choice = choice;
    }

    public int getBet () {
        return bet;
    }

    public void setBet (int bet) {
        this.bet = bet;
    }

    public int getWallet() {
        return wallet;
    }

    public void setWallet (int wallet) {
        this.wallet = wallet;
    }
    public void subtractWallet(int amount) {
        if (amount <= wallet) {
            wallet -= amount;
        } else {
            throw new IllegalArgumentException("Insufficient funds in the house wallet.");
        }
    }
}
