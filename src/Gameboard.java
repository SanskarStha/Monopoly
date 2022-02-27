
import java.util.Scanner;

public class Gameboard {

    /**
     * A constant variable that tells how many cells are there
     */
    public static int CELL_SIZE = 22;

    /**
     * The position where the Jail is located
     */
    public static int JAIL_POSITION = 8;

    /**
     * The position where the home is located
     */
    public static int HOME_POSITON = 0;

    /**
     * The array storing all cells.
     */
    private Cell[] cells = null;

    /**
     * The array storing all players
     */
    private Player[] players = {new Player("Kevin"), new Player("Sandy"), new Player("Emily")};;

    private int turn = 0;

    private boolean gameOver() {
        for (int i = 0; i < players.length; i++)
            if (players[i].getMoney() < 0)
                return true;
        return false;
    }

    public static Cell[] initCell() {
        Cell[] cells = new Cell[22];
        cells[0] = new FunctionCell("Home");
        cells[1] = new PropertyCell("RRS",500);
        cells[2] = UtilityPropertyCell.LIBRARY_UTILITY_CELL;
        cells[3] = new PropertyCell("LMC", 600);
        cells[4] = TrainStationPropertyCell.TRAIN_STATION_ARRAY[0];
        cells[5] = new PropertyCell("OEE", 600);
        cells[6] = new PropertyCell("OEW", 700);
        cells[7] = new PropertyCell("FSC", 800);
        cells[JAIL_POSITION] = new FunctionCell("Jail");
        cells[9] = TrainStationPropertyCell.TRAIN_STATION_ARRAY[1];
        cells[10] = new ChanceCell("Chance");
        cells[11] = new ParkCell();
        cells[12] = new PropertyCell("AAB", 1000);
        cells[13] = UtilityPropertyCell.CANTEEN_UTILITY_CELL;
        cells[14] = new PropertyCell("DLB", 1000);
        cells[15] = TrainStationPropertyCell.TRAIN_STATION_ARRAY[2];
        cells[16] = new PropertyCell("WLB", 1000);
        cells[17] = new PropertyCell("WHS", 1000);
        cells[18] = new PropertyCell("STB", 1000);
        cells[19] = new GotoJailCell();
        cells[20] = TrainStationPropertyCell.TRAIN_STATION_ARRAY[3];
        cells[21] = new ChanceCell("Comm Chest");
        return cells;
    }

    /**
     * The main logic of the program. It runs once only.
     */
    private void runOnce() {
        print();
        while (!gameOver()) {
            Player currentPlayer = players[turn];
            currentPlayer.roll();
            print();
            Cell currentCell = cells[currentPlayer.getPosition()];
            System.out.println("You have landed on: " + currentCell.getName());
            currentCell.event(currentPlayer, cells);
            turn = (turn + 1) % players.length; //next Player
        }
        System.out.println("Game over");
    }

    /**
     * The constructor of the class.
     */
    public Gameboard() {
        cells = initCell();
    }

    /**
     * Prints the status before each round.
     */
    private void print() {
        for (Player p : players)
            System.out.println(p.getName() + " : " + p.getMoney());
        for (int i = 0; i < cells.length; i++) {
            Cell c = cells[i];
            System.out.print(c.toString() + "\t\t");
            for (Player p : players) {
                if (p.getPosition() == i)
                    System.out.print(p.getName() + " ");
            }
            System.out.println();
        }
    }
    public static void main(String[] argv) {
            new Gameboard().runOnce();
    }
}
