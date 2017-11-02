public class Hand extends GroupOfCards {
    public final int NUM;
    private int shortest = 0;

    public Hand(int num, int maxNumOfCards) {
        super(maxNumOfCards);
        this.NUM = num;
    }

    public void sort() {
        if (getCurrentSize() == 0) return;
        int unsorted = getCurrentSize() - 1;
        for (int i = unsorted; i >= 0; i--) {
            int min = 1000;
            int index = -1;
            for (int j = 0; j <= i; j++) {
                int temp = getCard(j).getSuit() * 13 + getCard(j).getNum();
                if ( temp < min) {
                    min = temp;
                    index = j;
                }
            }
            addCard(removeCard(index));

        }
    }


    public void setShortest() {
        int numOfClubs = 0, numOfDiamonds = 0, numOfHearts = 0, numOfBlades = 0;
        for(int i = 0; i < getCurrentSize(); i++) {
            Card c = getCard(i);
            switch(c.getSuit()) {
                case(0): numOfClubs++;
                    break;
                case(1): numOfDiamonds++;
                    break;
                case(2): numOfHearts++;
                    break;
                case (3):
                    numOfBlades++;
                    break;
                default: break;
            }
        }


        shortest = 0;

        if ((numOfDiamonds <= numOfClubs || numOfClubs == 0)) {
            shortest = 1;
            if ((numOfBlades <= numOfDiamonds || numOfDiamonds == 0) && !find(12, 3) && !find(13, 3) && !find(14, 3)) {
                shortest = 3;
                if (numOfBlades == 0) {
                    shortest = 2;
                }
            }
        }
    }

    public int getShortest() {
        return shortest;
    }

    public Card playACard(Game game, Trick trick) {
        int index = -1;
        setShortest();
        if (trick.getCurrentSize() == 0 && (index = findHighest(shortest)) >= 0){

//            System.out.println("shortest 1: " + shortest + "index: " + index);

        }
        else if (trick.getCurrentSize() == game.PLAYERS - 1 && !trick.getHearts() && !trick.getQueen() && (index = findHighest(shortest)) >=0 ){
//            System.out.println("shortest 2: " + shortest + " index: " + index);
        }
        else if (trick.getWinningCard() != null && (index = findHighestBelow(trick.getWinningCard())) >= 0){
//            System.out.println("findHighestBelow - index: " + index);
        }
        else if (trick.getWinningCard() != null && (index = findMiddleHigh(game, trick.getWinningCard().getSuit())) >= 0) {
//            System.out.println("findMiddleHigh - index: " + index);
        }
        else if ((index = getIndex(12, 3)) >= 0); // queen of Spades
        else if ((index = getIndex(14, 3)) >= 0); // Ace of Spades
        else if ((index = getIndex(13, 3)) >= 0); // King of Spades
        else if ((index = findHighest(2)) >= 0); // heart
        else {
            index = findHighest();
//            System.out.println("findHighest() - index: " + index);

        }
        Card giveout = removeCard(index);
        trick.update(giveout, NUM);
        game.updateHeartsAndQueen(giveout);

        return giveout;



    }

    public int findLowest(int suit) {
        if (count(suit) == 0) return -1;

        int lowest = 20;
        for (int i = 0; i < getCurrentSize(); i++) {
            if (getCard(i).getSuit() == suit) {
                lowest = Math.min(lowest, getCard(i).getNum());
            }
        }
        return lowest;

    }

    public int count(int suit) {
        int count = 0;
        for(int i = 0; i < getCurrentSize(); i++) {
            if (getCard(i).getSuit() == suit) {
                count++;
            }
        }
        return count;
    }

    public boolean find(int num, int suit) {
        for(int i = 0; i < getCurrentSize(); i++) {
            if(getCard(i).getNum() == num && getCard(i).getSuit() == suit) {
                return true;
            }
        }
        return false;
    }

    public int getIndex(int num, int suit) {
        for (int i = 0; i < getCurrentSize(); i++) {
            if (getCard(i).getNum() == num && getCard(i).getSuit() == suit) {
                return i;
            }
        }
        return -1;
    }

    public int findHighest(int suit) {
        if (count(suit) == 0) return -1;

        int highest = 0;
        int index = -1;
        for (int i = 0; i < getCurrentSize(); i++) {
            if (getCard(i).getSuit() == suit) {
                highest = Math.max(highest, getCard(i).getNum());
                index = i;
            }
        }
        return index;
    }

    public int findLowest(Game game) {
        int lowest = 20;
        for (int i = 0; i < getCurrentSize(); i++) {
            Card temp = getCard(i);
            if (game.getHearts()) {
                lowest =  temp.getNum() < lowest ? temp.getNum() : lowest;
            } else {
                if (temp.getSuit() != 2) {
                    lowest =  temp.getNum() < lowest ? temp.getNum() : lowest;
                }
            }
        }
        return lowest;
    }

    public int findLastHigh(int suit) {
        int highest = findHighest(suit);
        if (highest == -1) return -1;
        if (suit == 3 && getCard(highest).getNum() == 12) {
            int idx = getIndex(12, 3);
            if (idx == 0) return highest;
            if (getCard(idx - 1).getSuit() == suit) return idx - 1;
            else return highest;
        }
        return highest;
    }

    public int findHighestBelow(Card winningCard) {
        int suit = winningCard.getSuit();
        int highest = findHighest(suit);
        int i = getIndex(highest, suit);
        while (highest > winningCard.getNum()) {
            i--;
            if (i < 0) return -1;
            if (getCard(i).getSuit() != suit) return -1;
            highest = getCard(i).getNum();
        }
        return i;
    }

    public int findMiddleHigh(Game game, int suit) {
        if (game.getQueenOfSpades() || suit != 3) {
            return findHighest(suit);
        } else {
            int index = getIndex(findLastHigh(suit), suit);

        }
        return -1;
    }

    public int findHighest() {
        int index = -1;
        int max = 0;
        for (int i = 0; i < getCurrentSize(); i++) {
            if (getCard(i).getNum() > max) {
                max = getCard(i).getNum();
                index = i;
            }
        }
        return index;
    }





}
