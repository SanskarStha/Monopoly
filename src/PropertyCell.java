
import java.util.Scanner;

public class PropertyCell extends Cell {
    protected int baseCost;
    protected Player owner = null;
    int house = 0;

    public Player getOwner() {
        return owner;
    }
    /**
     * Constructor
     */
    public PropertyCell(String name, int baseCost) {
        super(name);
        this.baseCost = baseCost;
    }

    public int getCost() {
        return baseCost;
    }

    public int getHouse() {
        return house;
    }

    @Override
    public String toString() {
        if (house == 0) {
            return name + " owned by " + (owner == null? "-" : owner.getName()) + " : " + baseCost;
        } else {
            return name + " owned by " + owner.getName() +
                    " : " + baseCost + " House : " + house;
        }
    }

    public int getRent(Player p) {

        return (int) (baseCost * (1 + house * 0.5));

    }

    @Override
    public void event(Player p , Cell[] cells) {
        System.out.println("You have landed on " + name + "!");
        Scanner in = new Scanner(System.in);
        if (owner == null) {
            System.out.print("Do you want to buy this property for $" + baseCost + "? (y/n): ");
            if (in.next().equals("y")) {
                System.out.println("You have bought this property!");
                owner = p;
                p.charge(baseCost);
            }
        } else if (p.getName().equals(owner.getName())){
            System.out.print("Do you want to build a house in " + name + " for $" + baseCost/5 + "? (y/n): ");
            if (in.next().equals("y")) {
                house += 1;
            }
        } else if (!p.getName().equals(owner.getName()) && !owner.isInPark()) {
            System.out.println(p.getName() + " has paid " + owner.getName() + " $" + getRent(p));
            p.charge(getRent(p));
            owner.charge(-getRent(p));
        } else if (!p.getName().equals(owner.getName()) && owner.isInPark()) {
            System.out.println(owner.getName() + " is in park so " + p.getName() + " does not have to pay rent.");
        }

    }

}
