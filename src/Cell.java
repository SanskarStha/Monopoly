
public abstract class Cell {
    protected String name;

    public final String getName() {
        return name;
    }

    public Cell(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }

    public abstract void event(Player p , Cell[] cells);
}
