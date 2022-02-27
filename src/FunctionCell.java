
public class FunctionCell extends Cell {

    public FunctionCell(String name) {
        super(name);
    }

    @Override
    public void event(Player p, Cell[] cells) {
        System.out.println("You have landed on " + name + "!");
    }

}


