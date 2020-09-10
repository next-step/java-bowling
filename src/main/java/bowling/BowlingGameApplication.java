package bowling;

import bowling.domain.BowlingGame;
import bowling.ui.BowlingGameDisplay;
import bowling.ui.BowlingGameInput;

import static bowling.ui.BowlingGameInput.inputPlayerName;

public class BowlingGameApplication {
    public static void main(String[] args) {
        final String playerName = inputPlayerName();
        BowlingGame.of(playerName)
            .gameLoop(BowlingGameInput::inputFallenPins, BowlingGameDisplay::diplayBoard);
    }
}
