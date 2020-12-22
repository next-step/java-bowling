package bowling.view;

import bowling.domain.PlayerName;

public interface InputView {
    int getNumberOfPlayers();

    String getPlayerName(int playerNo);

    int getKnockDownPins(PlayerName playerName);

    void printError(RuntimeException e);
}
