package bowling.view;

class AskNamePrintable extends Printable {
    private static int playerNumber = 1;

    AskNamePrintable() {
        append(String.format("플레이어 %d의 이름은(3 english letters)?: ", playerNumber++));
    }
}
