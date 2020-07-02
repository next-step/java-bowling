package bowling.step2;

import bowling.step2.domain.process.BowlingGame;
import bowling.step2.view.InputView;
import bowling.step2.view.OutputView;

public class BowlingGameMain {
    public static void main(String[] args) {
        BowlingGame bowlingGame = BowlingGame.of(InputView.inputName());

        while (!bowlingGame.isGameOver()){
            OutputView.viewFrameBoard(bowlingGame.play(InputView.inputPitch(bowlingGame)));
        }
    }
}
