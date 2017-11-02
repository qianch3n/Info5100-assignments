public class Deck extends GroupOfCards {

    public final int TOTAL_CARDS = 52;

    public Deck() {
        super(52);
        for(int suit = 0; suit < 4; suit++) {
            for (int num = 2; num < 15; num++) {
                this.addCard(new Card(num, suit));
            }
        }
    }


    public void shuffle() {
        int unshuffled = TOTAL_CARDS;
        for (int i = 0; i < TOTAL_CARDS; i++) {
            int randomIndex = (int) (Math.random() * unshuffled);
            unshuffled--;
            addCard(removeCard(randomIndex));
        }

    }

    public Card dealCard() {
        if (this.getCurrentSize() > 0) {
            return this.removeCard(0);
        }
        throw new IllegalArgumentException("No card to deal");
    }

    public static void main(String[] args) {

    }
}
