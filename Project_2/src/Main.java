
public class Main {

    public static void main(String[] args) {
//        Card cd = new Card(11, 2);
//        GroupOfCards group = new GroupOfCards(20);
//        group.addCard(cd);
//        group.addCard(new Card(12,3));
//        group.addCard(new Card(1,3));
//        group.addCard(new Card(2,3));
//        group.addCard(new Card(3,3));
//        group.addCard(new Card(9,3));
//
//
//
//
//        Deck d = new Deck();
//        d.shuffle();
//
//        Hand p = new Hand(1, 13);
//        for (int i = 0; i < 6; i++) {
//            p.addCard(group.getCard(i));
//        }
//        for ( int i = 0; i < 6; i++) {
//            p.getCard(i).display();
//        }
//        p.sort();
//        System.out.println("sorted...........");
//        for (int i = 0; i < 6; i++) {
//            p.getCard(i).display();
//        }
//        p.setShortest();
//        System.out.println(p.getShortest());
//        System.out.println("findLowest: " + p.findLowest(1));
//        System.out.println("count: " + p.count(1));
//        System.out.println("find: " + p.find(12,3));
//        System.out.println("getIndex: " + p.getIndex(12,3));
//        System.out.println("findHighest: " + p.findHighest(3));
//        System.out.println("findLastHighest: " + p.findLastHigh(3));
//        System.out.println("findHighestBelow: " + p.findHighestBelow(new Card(4, 0)));

        Game game = new Game(4);
        game.playAGame();




    }
}
