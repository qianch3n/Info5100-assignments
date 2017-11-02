public class Trick extends GroupOfCards {

    private int winner;
    private boolean heart = false;
    private boolean queen = false;
    private Card winningCard;


    public Trick(int players) {
        super(2 * players - 1);
    }

    public int getWinner() {
        return winner;
    }

    public Card getWinningCard() {
        return winningCard;
    }

    public boolean getHearts() {
        return heart;
    }

    public boolean getQueen() {
        return queen;
    }

    public void setWinningCard(int num, int suit) {
        winningCard = new Card(num, suit);
    }

    private boolean isWinner(Card card) {
        if (winningCard == null) {
            return true;
        } else if (winningCard.getSuit() == card.getSuit() && card.getNum() > winningCard.getNum()) {
            return true;
        }
        return false;

    }

    public void update(Card card, int index) {
        if(isWinner(card)) {
            setWinningCard(card.getNum(), card.getSuit());
            winner = index;
        }
        if (card.getSuit() == 2) {
            heart = true;
        }
        if (card.getSuit() == 3 && card.getNum() == 12) {
            queen = true;
        }
    }
}
