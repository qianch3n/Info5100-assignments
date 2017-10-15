public class IceCream extends DessertItem {
    int price;

    public IceCream(String name, int price) {
        super(name);
        this.price = price;
    }

    public int getCost() {
        return price;
    }
}