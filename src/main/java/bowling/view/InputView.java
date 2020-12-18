package bowling.view;

public interface InputView {
    String getPlayerName();

    int getKnockDownPins(int frameNo);

    void printError(RuntimeException e);
}
