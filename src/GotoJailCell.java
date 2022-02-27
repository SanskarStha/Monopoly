
class GotoJailCell extends FunctionCell {

    public GotoJailCell() {
        super("Goto Jail");
    }

    @Override
    public void event(Player p, Cell[] cells) {
        System.out.println("You have landed on " + name + "!");
        System.out.println(p.getName() + ", you have to go to Jail.");
        p.putToJail(); // put the player into the Jail
    }
}
