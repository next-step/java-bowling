package bowling;

import bowling.domain.BowlingGame;
import bowling.domain.User;
import bowling.dto.BowlingGameDto;
import bowling.view.InputView;
import bowling.view.ResultView;

import java.util.List;

public class BowlingGameMain {
    public static void main(String[] args) throws Exception {

        List<User> users = InputView.getPlayerNames(InputView.getHowMany());
        BowlingGame bowlingGame = BowlingGame.valueOf(users);
        BowlingGameDto bowlingGameDto = BowlingGameDto.valueOf(bowlingGame);
        ResultView.printBowlingGame(bowlingGameDto);
        while (!bowlingGame.isFinished()) {
            int score = InputView.getScore(bowlingGame.nowPlayer().getUser());
            bowlingGame = bowlingGame.addScore(score);
            bowlingGameDto = BowlingGameDto.valueOf(bowlingGame);
            ResultView.printBowlingGame(bowlingGameDto);
        }
    }
}
