import java.util.Scanner;

public class Game {
    public final int PLAYERS;
    private Deck deck;
    private Hand[] players;
    private Trick[] tricks;
    private int numOfTricks;
    private boolean hearts = false;
    private boolean queenOfSpades = false;

    Game(int numOfPlayers) {
        deck = new Deck();
        this.PLAYERS = numOfPlayers;
        players = new Hand[numOfPlayers];
        int numOfCards = 52 / numOfPlayers;
        for(int i = 0; i < numOfPlayers; i++) {
            players[i] = new Hand(i, numOfCards);
        }
        tricks = new Trick[numOfCards];

    }

    public int getNumOfTricks() {
        return numOfTricks;
    }

    public boolean getHearts() {
        return hearts;
    }

    public boolean getQueenOfSpades() {
        return queenOfSpades;
    }

    public void playAGame(){
        deck.shuffle();
        int cardsLeft = 52 % PLAYERS;
        for (int i = 0; i < 52 / PLAYERS; i++) {
            for (int j = 0; j < PLAYERS; j++) {
                players[j].addCard(deck.dealCard());
            }
        }
        for (Hand player : players) {
            player.sort();
            player.setShortest();
        }

        int lowestClub = 20;
        int playNum = -1;
        for (int i = 0; i < PLAYERS; i++) {
            System.out.println("Player " + i + " shortest = " + players[i].getShortest());
            for (int j = 0; j <players[i].getCurrentSize(); j++) {
                Card card = players[i].getCard(j);
                card.display();
                if (card.getSuit() == 0) {
                    if (card.getNum() < lowestClub) {
                        playNum = i;
                        lowestClub = card.getNum();
                    }
                }
            }
        }
        System.out.println();
        for (int i = 0; i < tricks.length; i++) {
            System.out.println();
            System.out.println("Round " + (i+1));
            tricks[i] = new Trick(PLAYERS);
            int index = playNum;
            for (int j = 0; j < PLAYERS; j++) {
                Card card = new Card(0, 0);
                if (i == 0 && j == 0) {
                    card = players[index].removeCard(0);
                } else {
                    card = players[index].playACard(this, tricks[i]);
                }
                tricks[i].addCard(card);
                tricks[i].update(card, index);
                System.out.print("Player " + index + "          ");
                card.display();
                updateHeartsAndQueen(card);

                if (++index == PLAYERS) index = 0;
            }
            if (i == 0) {
                for (int n = 0; n < cardsLeft; n++ ) {
                    Card c = deck.dealCard();
                    tricks[i].addCard(c);
                    System.out.print("Undelt Card " + index + "     ");
                    c.display();
                    updateHeartsAndQueen(c);
                }
            }
            playNum = tricks[i].getWinner();

        }
        System.out.println();
        for (int i = 0; i < PLAYERS; i ++) {
            System.out.println("Player " + i + ": score = " + computePoints(i));
        }

        System.out.println("Play another game? Y/N");
        Scanner input = new Scanner(System.in);
        String guess = input.nextLine().toLowerCase();
        if (guess.equals("y")) {
            deck = new Deck();
            playAGame();
        }


    }

    public void updateHeartsAndQueen(Card card) {
        if (hearts == false) {
            if (card.getSuit() == 2) {
                hearts = true;
                System.out.println("Hearts is now broken.");
            }
        }
        if (card.getSuit() == 3 && card.getNum() == 12) {
            queenOfSpades = true;
        }

    }

    private int computePoints(int playerNum) {
        int points = 0;
        for(Trick t : tricks) {
            if (t.getWinner() == playerNum) {
                for (int i = 0; i < t.getCurrentSize(); i++) {
                   if (t.getCard(i).getSuit() == 2) points++;
                   if (t.getCard(i).getSuit() == 3 && t.getCard(i).getNum() == 12) points += 13;
                }
            }
        }
        return points;

    }



}
