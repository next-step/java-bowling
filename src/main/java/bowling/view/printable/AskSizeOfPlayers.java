package bowling.view.printable;

public class AskSizeOfPlayers extends Printable {
    @Override
    public void print() {
        print(lineSeparator);
        print("How many people? ");
    }
}
