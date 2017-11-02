public class GroupOfCards {
    private Card[] cards;
    private int currentSize = 0;



    public GroupOfCards(int size) {
        cards = new Card[size];
    }

    public int getCurrentSize() {
        return currentSize;
    }

    public Card getCard(int i) {
        return cards[i];
    }

    public void addCard(Card c) {
        if (currentSize == cards.length) {
            return;
        }
        cards[currentSize++] = c;
    }

    public Card removeCard(int i) {
        if (i > currentSize || i < 0) {
            throw new IllegalArgumentException("Index out of bound.");
        }
        Card removed = cards[i];
        for (int k = i; k < currentSize - 1; k++) {
            cards[k] = cards[k+1];
        }
        cards[currentSize - 1] = null;
        currentSize--;
        return removed;
    }


    public void dispaly() {
        for(int i = 0; i < currentSize; i++) {
            cards[i].display();
        }
    }

}
