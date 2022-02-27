
import java.util.concurrent.ThreadLocalRandom;

public class Player {
    private final String name;
    private int money;
    private int position;
    private int lastMove;
    private boolean inPark;
    private boolean inJail;

    public Player(String name) {
        this.name = name;
        money = 10000;
        position = Gameboard.HOME_POSITON;
        inPark = false;
        inJail = false;
    }

    /**
     * A player roll a dice and change its position
     */
    public void roll() {
        inPark = false; /* assigning false boolean value to inPark to indicate
                           the player moves out of the park in the current round if the player
                           was in the park in the last round */
        if (isInJail()) {
            System.out.println(name + ", sorry you are in Jail. Skip one round.");
            // assigning false boolean value to inJail so that the player in Jail can play in another round
            inJail = false;
        } else {
            lastMove = ThreadLocalRandom.current().nextInt(1, 7);
            System.out.println(getName() + ", this is your turn. We roll for you\nIt is..." + lastMove);
            position = (position + lastMove) % Gameboard.CELL_SIZE; /* changes player's position and restarts position
                                                                from Home position if the position exceeds cell size */
        }
        // giving $2000 each time player passes through or arrives Home
        if (getPosition() - getLastMove() < Gameboard.HOME_POSITON) {
            charge(-2000);
            System.out.println("$2000 has been added to you, " + name);
        }
    }

    public boolean isInPark() {
        return inPark;
    }

    public void setInPark(boolean inPark) {
        this.inPark = inPark;
    }

    public boolean isInJail() {
        return getPosition() == Gameboard.JAIL_POSITION && inJail;
    }

    public void putToJail() {
        position = Gameboard.JAIL_POSITION;
        inJail = true;
    }

    public int getLastMove() {
        return lastMove;
    }

    public int getMoney() {
        return money;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public void charge(int money) {
        this.money -= money;
    }

}
