package bowling.view;

class AskCountOfPinsPrintable extends Printable {
    AskCountOfPinsPrintable(String prefix) {
        append(lineSeparator);
        append(String.format("%s프레임 투구 : ", prefix));
    }
}
