package bowling.view.printable;

public class AskNamePrintable extends Printable {
    @Override
    public void print() {
        print("플레이어 이름은(3 english letters)?: ");
    }
}
