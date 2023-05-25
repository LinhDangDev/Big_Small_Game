package BigGame;

import java.util.Scanner;

public class BigSmallGame {

    public static void main(String[] args) throws IllegalArgumentException {
        int playerWalletStart = 0;
        int houseWalletStart = 1000000;
        Player player = new Player();
        House house = new House();
        Scanner sc = new Scanner(System.in);
        house.addWallet (houseWalletStart);
        boolean choose = true;
        int round = 1;


        while (playerWalletStart <= 0) {
            try {
                System.out.print("Enter amount available: ");
                playerWalletStart = Integer.parseInt(sc.nextLine());
                player.setWallet(playerWalletStart);
                if (playerWalletStart <= 0) {
                    throw new IllegalArgumentException("Value must be a positive number. Please enter the amount available again.");
                }
            } catch (NumberFormatException ignored) {
                System.out.println("Invalid number. Please enter the amount available again.");
            } catch (IllegalArgumentException ignored) {
                System.out.println(ignored.getMessage());
            }
        }

        System.out.println("The House has $" + houseWalletStart);
        System.out.println("The Player has $" + player.getWallet());
        System.out.println("Try your luck to win all the money of the house!");

        do {
            System.out.println("Round " + round + ":");
            double bet = 0;

            do {
                try{
                System.out.println("How much do you want to bet?");
                player.setBet(Integer.parseInt(sc.nextLine()));
                    if (player.getBet() > player.getWallet()) {
                        throw new IllegalArgumentException("The amount you bet is higher than the money you have. Enter again!");
                    }
                }catch (NumberFormatException ignored) {
                    System.out.println("Invalid number. Please enter the amount available again.");
                } catch (IllegalArgumentException ignored) {
                    System.out.println(ignored.getMessage());
                }
            } while (player.getBet() > player.getWallet ());

            System.out.println("You have bet $" + player.getBet());


            String choice = "";
            do {
                System.out.println("Do you want to bet big or small? (big/small)");
                choice = sc.nextLine();
                player.setChoice(choice);
                if (!player.getChoice().equals("small") && !player.getChoice().equals("big")) {
                    throw new IllegalArgumentException ("Invalid choice. Please enter a valid choice.");
                }
            } while (!player.getChoice().equals("small") && !player.getChoice().equals("big"));

            house.rollDices();
            house.printDices();

            System.out.println("The sum of 3 dices is " + house.sumDices() + "!");


            //handle bet player
            int sum = house.sumDices();
            if ((sum >= 4 && sum <= 10) && player.getChoice().equals("small")) {
                System.out.println("Congratulations! You win!");
                int winnings = player.getBet();
                player.setWallet (winnings);
                house.subtractWallet(winnings);
            } else if ((sum >= 11 && sum <= 17) && player.getChoice().equals("big")) {
                System.out.println("Congratulations! You win!");
                int winnings = player.getBet();
                player.setWallet (winnings);
                house.subtractWallet(winnings);
            } else {
                System.out.println("Sorry, you lose!");
                int losses = player.getBet();
                player.subtractWallet(losses);
                house.addWallet(losses);
            }


            System.out.println("The house after play game has $" + house.getWallet());
            System.out.println("The player after play game has $" + player.getWallet());

            if (player.getWallet() == 0) {
                System.out.println("You are out of money!");
                break;
            }
            if (house.getWallet() == 0) {
                System.out.println("The house is out of money!");
                break;
            }


            System.out.println("Do you still want to continue playing? (true/false)");
            choose = sc.nextBoolean();
            sc.nextLine(); // Consume newline character

            round++; //  round value has increase 1

        } while (choose);
    }
}
