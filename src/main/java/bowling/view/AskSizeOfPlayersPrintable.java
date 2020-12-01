package bowling.view;

public class AskSizeOfPlayersPrintable extends Printable {
    @Override
    public void print() {
        print(lineSeparator);
        print("How many people? ");
    }
}
