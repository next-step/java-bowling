package bowling;

import bowling.domain.Bowling;
import bowling.domain.User;
import bowling.dto.BowlingDto;
import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingGameMain {
    public static void main(String[] args) throws Exception {
        User user = new User(InputView.getPlayerName());
        Bowling bowling = new Bowling(user);
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
