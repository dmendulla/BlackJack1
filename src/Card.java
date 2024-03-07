public class Card {
    private int num;
    private int suit;
    public Card(int num, int suit) {
        this.num = num;
        this.suit = suit;
    }

    public int getNum() {
        return num;
    }
    public int getSuit() {
        return suit;
    }

    @Override
    public String toString() {
        String[] values = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
        String[] suits = {"♥", "♦", "♣", "♠"};
        return values[this.num-2] + " " + suits[this.suit];
    }
}