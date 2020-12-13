package bowling.view;

public interface InputView {
    String getPlayerName();

    int getKnockDownPins(int frameIndex);

    void printError(RuntimeException e);
}
