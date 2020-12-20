package bowling.view;

public interface InputView {
    int getNumberOfPlayers();

    String getPlayerName();

    int getKnockDownPins(int frameNo);

    void printError(RuntimeException e);
}
