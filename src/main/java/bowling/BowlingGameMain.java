package bowling;

import bowling.domain.BowlingGame;
import bowling.domain.User;
import bowling.dto.BowlingDto;
import bowling.view.InputView;

import java.util.ArrayList;
import java.util.List;

public class BowlingGameMain {
    public static void main(String[] args) throws Exception {

        List<User> users = InputView.getPlayerNames(InputView.getHowMany());
        BowlingGame bowlingGame = BowlingGame.valueOf(users);
        List<BowlingDto> bowlingDtos = new ArrayList<>();
        BowlingDto bowlingDto = BowlingDto.valueOf(bowling);
        ResultView.printBowling(bowlingDto);

        while (!bowling.isFinished()) {
            int score = InputView.getScore(bowling.nowFrame() + 1);
            bowling = bowling.addScore(score);
            bowlingDto = BowlingDto.valueOf(bowling);
            ResultView.printBowling(bowlingDto);
        }
    }
}
