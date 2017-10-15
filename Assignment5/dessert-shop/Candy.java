public class Candy extends DessertItem {
    double weight;
    int price;

    public Candy(String name, double weight, int price) {
        super(name);
        this.weight = weight;
        this.price = price;
    }

    public int getCost() {
        double res = weight * price;
        res = Math.round(res);
        return (int) res;
    }

    public String getWeight() {
        return Double.toString(weight);
    }

    public String getPrice() {
        return DessertShoppe.cents2dollarsAndCents(price);
    }



}