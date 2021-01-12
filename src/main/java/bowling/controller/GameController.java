package bowling.controller;

import bowling.domain.BowlingGame;
import bowling.view.InputView;

public class GameController {

    public void play(){
        BowlingGame bowlingGame = new BowlingGame(InputView.inputName());
        bowlingGame.run();
    }
}
