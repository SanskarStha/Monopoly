
import java.util.Scanner;

/**
 * If the owner owns only one train station, the rent is 500
 * If the owner owns two of them, the rent is 1000
 * If the owner owns three of them, the rent is 2000
 * If the owner owns four of them, the rent is 4000
 */
public class TrainStationPropertyCell extends PropertyCell {
    public static final int TRAIN_STATION_COST = 500;
    public static final TrainStationPropertyCell[] TRAIN_STATION_ARRAY = {
            new TrainStationPropertyCell("Kowloon"),
            new TrainStationPropertyCell("Mongkok"),
            new TrainStationPropertyCell("Central"),
            new TrainStationPropertyCell("Shatin")
    };

    public TrainStationPropertyCell(String name) {
        super(name, TRAIN_STATION_COST);
    }

    @Override
    public int getRent(Player p) {
        int trainStationsOwned = 0;
        for (int i = 0; i < 4; i++) {
            if (TRAIN_STATION_ARRAY[i].getOwner() != null) {
                if (owner.getName().equals(TRAIN_STATION_ARRAY[i].getOwner().getName())) {
                    trainStationsOwned += 1; // calculating the total number of train stations owned by the owner
                }
            }
        }

        switch (trainStationsOwned) {
            case 1:
                return TRAIN_STATION_COST; // rent is 500
            case 2:
                return TRAIN_STATION_COST * 2; // rent is 1000
            case 3:
                return TRAIN_STATION_COST * 4; // rent is 2000
            case 4:
                return TRAIN_STATION_COST * 8; // rent is 4000
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
        } else if (p.getName().equals(owner.getName())){
            System.out.println("It is a train station property so you can not build house on it.");
        } else if (!p.getName().equals(owner.getName()) && !owner.isInPark()) {
            System.out.println(p.getName() + " has paid " + owner.getName() + " $" + getRent(p));
            p.charge(getRent(p));
            owner.charge(-getRent(p));
        } else if (!p.getName().equals(owner.getName()) && owner.isInPark()) {
            System.out.println(owner.getName() + " is in park so " + p.getName() + " does not have to pay rent.");
        }
    }
}
