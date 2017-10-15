public class Cookie extends DessertItem {
    int number;
    int price;

    public Cookie(String name, int number, int price) {
        super(name);
        this.number = number;
        this.price = price;
    }

    public int getCost() {
        double res = number * price / 12;
        res = Math.round(res);
        return (int) res;
    }

    public String getNumber() {
        return Integer.toString(number);
    }

    public String getPrice() {
        return DessertShoppe.cents2dollarsAndCents(price);
    }
}