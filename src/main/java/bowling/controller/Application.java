package bowling.controller;

import bowling.domain.dto.BowlingGameDto;
import bowling.domain.game.MultiBowlingGame;
import bowling.domain.score.PitchScore;
import bowling.view.InputView;
import bowling.view.OutputView;

import java.util.List;

public class Application {

    public static void main(String[] args) {
        MultiBowlingGame multiBowlingGame = MultiBowlingGame.of(InputView.inputPlayerNames());
        OutputView.printDefaultScoreBoard(multiBowlingGame);

        while (multiBowlingGame.hasNextTurn()) {
            PitchScore pitchScore = PitchScore.valueOf(InputView.inputPitchScore(multiBowlingGame));
            multiBowlingGame.bowl(pitchScore);

            List<BowlingGameDto> bowlingGameDtos = multiBowlingGame.getFrameDtos();
            OutputView.printTest(bowlingGameDtos);

            multiBowlingGame.moveToNextFrame();
        }
    }
}
