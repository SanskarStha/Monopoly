
public class ParkCell extends FunctionCell {

    public ParkCell() {
        super("Car Park");
    }

    @Override
    public void event(Player p, Cell[] cells) {
        System.out.println("You have landed on " + name + "!");
        p.setInPark(true);
    }
}

