package BigGame;

import java.util.Scanner;

public class BigSmallGame {

    public static void main(String[] args) throws IllegalArgumentException {
        int playerWalletStart = -1;
        int houseWalletStart = 1000;
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
                System.out.println("How much do you want to bet?");
                player.setBet(Integer.parseInt(sc.nextLine()));
                if (player.getBet() > player.getWallet ()) {
                    System.out.println("The amount you bet is higher than the money you have. Enter again!");
                }
            } while (player.getBet() > player.getWallet ());
            System.out.println("You have bet $" + player.getBet());

            String choice = "";
            do {
                System.out.println("Do you want to bet big or small? (big/small)");
                choice = sc.nextLine();
                player.setChoice(choice);
                if (!player.getChoice().equals("small") && !player.getChoice().equals("big")) {
                    System.out.println("Invalid choice. Please enter a valid choice.");
                }
            } while (!player.getChoice().equals("small") && !player.getChoice().equals("big"));

            house.rollDices();
            house.printDices();

            System.out.println("The sum of 3 dices is " + house.sumDices() + "!");

            int sumDices = house.sumDices();

            if (sumDices >= 4 && sumDices <= 10 && player.getChoice().equals("small")) {
                System.out.println("You won $" + player.getBet());
                player.setWallet(player.getWallet() + player.getBet());
                house.subtractWallet(player.getBet());
            } else if (sumDices >= 11 && sumDices <= 17 && player.getChoice().equals("big")) {
                System.out.println("You won $" + player.getBet());
                player.setWallet(player.getWallet() + player.getBet());
                house.subtractWallet(player.getBet());
            } else if (sumDices == 3 || sumDices == 18 && player.getChoice().equals("same")) {
                System.out.println("You lose $" + player.getBet());
                player.setWallet(player.getWallet() - player.getBet());
                house.addWallet(player.getBet());
            }

            System.out.println("The house has $" + house.getWallet());
            System.out.println("The player has $" + player.getWallet());

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
            round++;

        } while (choose);
    }
}
