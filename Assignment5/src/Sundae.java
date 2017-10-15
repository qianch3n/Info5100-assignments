public class Sundae extends IceCream {
    String topping;
    int toppingPrice;

    public Sundae(String name, int price, String topping, int toppingPrice) {
        super(name, price);
        this.topping = topping;
        this.toppingPrice = toppingPrice;
    }

    public int getCost() {
        return price + toppingPrice;
    }

    public String getTopping() {
        return topping;
    }
}