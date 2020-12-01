package bowling.view;

public class AskCountOfPinsPrintable extends Printable {

    private final String prefix;

    AskCountOfPinsPrintable(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public void print() {
        print(lineSeparator);
        print(String.format("%s프레임 투구 : ", prefix));
    }
}
