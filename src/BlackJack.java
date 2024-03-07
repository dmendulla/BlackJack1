import java.util.ArrayList;
import java.util.Scanner;

public class BlackJack {

    private Deck deck;
    private ArrayList<Card> player;
    private int playerMoney;
    private ArrayList<Card> dealer;

    Scanner kb;

    public BlackJack() {
        deck = new Deck();
        player = new ArrayList<>();
        dealer = new ArrayList<>();
        playerMoney = 500;
        kb = new Scanner(System.in);

    }

    public static void main(String[] args) {
        BlackJack game = new BlackJack();
        game.run();
    }

    private int calculateScore(ArrayList<Card> hand) {
        int score = 0;
        int aceCount = 0;
        for (Card card : hand) {
            int cardValue = card.getNum();
            if (cardValue >= 2 && cardValue <= 10) {
                score += cardValue;
            } else if (cardValue >= 11 && cardValue <= 13) {
                score += 10;
            } else if (cardValue == 14) {
                aceCount++;
                score += 11;
            }
        }

        while (score > 21 && aceCount > 0) {
            score -= 10;
            aceCount--;
        }

        return score;
    }

    private void run() {
        // the code block below shows what initial cards the player and dealer gets at the  beginning of the game.
        deck.shuffle();

        player.add(deck.getCard());
        dealer.add(deck.getCard());
        player.add(deck.getCard());

        int playerScore = calculateScore(player);
        int dealerScore = calculateScore(dealer);

        System.out.println("Player's hand:\t" + player.get(0) + " " + player.get(1));
        System.out.println("Dealer's hand:\t" + dealer.get(0) + " [?] ");

        System.out.println("Player's score: " + playerScore);
        System.out.println("Dealer's score: " + dealerScore);

        System.out.println("Your money: $" + playerMoney);

        System.out.println();
        System.out.println("How much do you want to bet?");

        int bet = kb.nextInt();
        kb.nextLine();

        while (bet <= 0 || bet > playerMoney) {
            System.out.println("Please enter a valid amount of money you want to bet.");
            bet = kb.nextInt();
            kb.nextLine();
        }

        while (playerScore < 21) {
            System.out.println();
            System.out.println("hit or stay?");
            String response = kb.nextLine();
            // the line below shows the output if player chooses "hit" so the method will repeat the question until player reaches above 21 or 21.
            if (response.toLowerCase().equals("hit")) {
                player.add(deck.getCard());
                playerScore = calculateScore(player);

                System.out.print("Player's hand:\t");
                for (Card card : player) {
                    System.out.print(card + " ");
                }
                System.out.println();

                System.out.print("Dealer's hand:\t" + dealer.get(0) + " [?] ");
                System.out.println();

                System.out.println("Player's score: " + playerScore);
                System.out.println("Dealer's score: " + dealerScore);
            // the line below is made if a player decides to "stay" in the game, so it's dealer's turn to drew cards until he reaches above 17 and below 21 score.
            } else if (response.toLowerCase().equals("stay")) {
                while (dealerScore <= 17) {
                    dealer.add(deck.getCard());
                    dealerScore = calculateScore(dealer);
                }

                System.out.print("Player's hand:\t");
                for (Card card : player) {
                    System.out.print(card + " ");
                }
                System.out.println();

                System.out.print("Dealer's hand:\t");
                for (Card card : dealer) {
                    System.out.print(card + " ");
                }
                System.out.println();

                System.out.println("Player's score: " + playerScore);
                System.out.println("Dealer's score: " + dealerScore);
                // the lines below represents the finalized outputs of whether player wins or loses. It also shows in which conditions player could win or lose.
                if (playerScore == 21) {
                    System.out.println("Player wins");
                    playerMoney += bet;
                } else if (playerScore > 21) {
                    System.out.println("Dealer wins");
                    playerMoney -= bet;

                }

                if (dealerScore >= 17 && dealerScore <= 21) {
                    System.out.println("Dealer wins");
                    playerMoney -= bet;
                } else if (dealerScore > 21) {
                    System.out.println("Player wins");
                    playerMoney += bet;
                }

                if (dealerScore == playerScore) {
                    System.out.println("Push");
                }

            } else if (response != "hit" || response != "stay") {
                System.out.println("Please type 'hit' or 'stay'");
            }
            break;
            }
        }
    }