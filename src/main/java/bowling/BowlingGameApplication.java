package bowling;

import bowling.domain.BowlingGame;
import bowling.ui.BowlingGameDisplay;
import bowling.ui.BowlingGameInput;

import static bowling.ui.BowlingGameInput.inputPlayers;

public class BowlingGameApplication {
    public static void main(String[] args) {
        BowlingGame.of(inputPlayers())
                   .gameLoop(BowlingGameInput::inputPlayerFallenPins, BowlingGameDisplay::diplayBoard);
    }
}
