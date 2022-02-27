
import java.util.concurrent.ThreadLocalRandom;

/**
 * Got one of the following chances randomly:
 * 1. Roll again
 * 2. +1000
 * 3. -1000
 * 4. Move to Jail directly without getting the 2000.
 */
public class ChanceCell extends FunctionCell {

    public ChanceCell(String name) {
        super(name);
    }

    public void event(Player p, Cell[] cells) {
        System.out.println("You have landed on " + name + "!");
        int chance = ThreadLocalRandom.current().nextInt(1, 5); // getting one out of four random chances
        System.out.print(name + " result: ");
        switch (chance) {
            case 1:
                System.out.println("Roll again!");
                p.roll();
                break;
            case 2:
                System.out.println("Add $1000");
                p.charge(-1000);
                break;
            case 3:
                System.out.println("Deduct $1000");
                p.charge(1000);
                break;
            case 4:
                System.out.println("Go to Jail!");
                p.putToJail();
                break;
        }
    }


}