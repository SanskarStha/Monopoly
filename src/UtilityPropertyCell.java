
import java.util.Scanner;

/**
 * The rent of UtilityPropertyCell is computed by the number of steps that the player rolled multiply by x
 * x = 100, if both property are owned by the same player
 * x = 10, if the owner of this property cell owns only one property cell.
 */
public class UtilityPropertyCell extends PropertyCell {
    public static final int UTILITY_PROPERTY_COST = 500;
    public static final UtilityPropertyCell LIBRARY_UTILITY_CELL = new UtilityPropertyCell("Library", UTILITY_PROPERTY_COST);
    public static final UtilityPropertyCell CANTEEN_UTILITY_CELL = new UtilityPropertyCell("Canteen", UTILITY_PROPERTY_COST);

    public UtilityPropertyCell(String name, int utilityPropertyCost) {
        super(name, utilityPropertyCost);
    }

    @Override
    public int getRent(Player p) {
        if (LIBRARY_UTILITY_CELL.getOwner() != null && CANTEEN_UTILITY_CELL.getOwner() != null) {
            if (LIBRARY_UTILITY_CELL.getOwner().getName().equals(CANTEEN_UTILITY_CELL.getOwner().getName())) {
                // if the owner owns both utility properties
                return p.getLastMove() * 100;
            } else { // if the owner owns only one of the utility properties
                return  p.getLastMove() * 10;
            }
        } else if (LIBRARY_UTILITY_CELL.getOwner() == null && CANTEEN_UTILITY_CELL.getOwner() != null
                || LIBRARY_UTILITY_CELL.getOwner() != null && CANTEEN_UTILITY_CELL.getOwner() == null) {
            return p.getLastMove() * 10; // if the owner owns only one of the utility properties
        }
        return 0;
    }

    @Override
    public void event(Player p, Cell[] cells) {
        System.out.println("You have landed on " + name + "!");
        Scanner in = new Scanner(System.in);
        if (owner == null) {
            System.out.print("Do you want to buy this property for $" + baseCost + "? (y/n): ");
            if (in.next().equals("y")) {
                System.out.println("You have bought this property!");
                owner = p;
                p.charge(baseCost);
            }
        } else if (p.getName().equals(owner.getName())) {
            System.out.println("It is a utility property so you can not build house on it.");
        } else if (!p.getName().equals(owner.getName()) && !owner.isInPark()) {
            System.out.println(p.getName() + " has paid " + owner.getName() + " $" + getRent(p));
            p.charge(getRent(p));
            owner.charge(-getRent(p));
        } else if (!p.getName().equals(owner.getName()) && owner.isInPark()) {
            System.out.println(owner.getName() + " is in park so " + p.getName() + " does not have to pay rent.");
        }
    }


}
