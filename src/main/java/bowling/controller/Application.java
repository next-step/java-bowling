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
        List<BowlingGameDto> bowlingGameDtos = multiBowlingGame.getFrameDtos();
        OutputView.printDefaultScoreBoard(bowlingGameDtos);

        while (multiBowlingGame.hasNextTurn()) {
            String currentPlayerName = multiBowlingGame.getCurrentPlayerName();
            PitchScore pitchScore = PitchScore.valueOf(InputView.inputPitchScore(currentPlayerName));
            multiBowlingGame.bowl(pitchScore);

            bowlingGameDtos = multiBowlingGame.getFrameDtos();
            OutputView.printBowlingScoreBoard(bowlingGameDtos);

            multiBowlingGame.moveToNextFrame();
        }
    }
}
