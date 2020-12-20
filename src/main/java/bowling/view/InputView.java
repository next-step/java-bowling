package bowling.view;

import bowling.domain.PlayerName;

public interface InputView {
    int getNumberOfPlayers();

    String getPlayerName();

    int getKnockDownPins(int frameNo);

    int getKnockDownPins2(PlayerName playerName);

    void printError(RuntimeException e);
}
