package bowling.view;

class AskSizeOfPlayersPrintable extends Printable {
    AskSizeOfPlayersPrintable() {
        append(lineSeparator);
        append("How many people? ");
    }
}
