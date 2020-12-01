package bowling.view;

class AskRollPrintable extends Printable {
    AskRollPrintable(String prefix) {
        append(lineSeparator);
        append(String.format("%s프레임 투구 : ", prefix));
    }
}
