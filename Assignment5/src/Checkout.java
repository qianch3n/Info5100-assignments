import java.util.*;

public class Checkout {
    Vector<DessertItem> items = new Vector<>();

    public int numberOfItems() {
        return items.size();
    }

    public void enterItem(DessertItem item) {
        items.add(item);
    }

    public void clear() {
        items.clear();
    }

    public int totalCost() {
        int totalCost = 0;
        for (DessertItem item : items) {
            totalCost += item.getCost();
        }
        return totalCost;
    }

    public int totalTax() {
        double res = totalCost() * DessertShoppe.TAX_RATE;
        res = Math.round(res);
        return (int) res;
    }

    public String toString() {
        
        String receipt = "          " + DessertShoppe.NAME + "          \n" +
        "          " + "--------------------" + "          \n\n";
        for (DessertItem item : items) {
            String name = "";
            if ( item instanceof Candy) {
                Candy c = (Candy) item;
                receipt += c.getWeight() + " lbs. @ " + c.getPrice() + " /lb.\n";
            } else if ( item instanceof Cookie) {
                Cookie c = (Cookie) item;
                receipt += c.getNumber() + " @ " + c.getPrice() + " /dz.\n";
            } else if ( item instanceof Sundae) {
                Sundae s = (Sundae) item;
                receipt += s.getTopping() + " Sundae with\n";
            }
            name = item.getName();
            String cost = DessertShoppe.cents2dollarsAndCents(item.getCost());
            receipt += name + getSpaces(name, cost) + cost + "\n";
        }
        String tax = DessertShoppe.cents2dollarsAndCents(totalTax());
        String totalCost = DessertShoppe.cents2dollarsAndCents(totalCost() + totalTax());
        receipt += "\n" + "Tax" + getSpaces("tax", tax) + tax + "\n" + "Total Cost" + getSpaces("Total Cost", totalCost) + totalCost+ "\n";
        return receipt;
    }

    public String getSpaces(String left, String right) {
        String output = "";
        int spaces = DessertShoppe.MAX_RECEIPT_WIDTH - left.length() - right.length();
        for ( int i = 0; i < spaces; i++) {
            output += " ";
        }
        return output;
    }

}