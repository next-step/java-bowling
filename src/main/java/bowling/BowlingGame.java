package bowling;

import bowling.domain.*;
import bowling.domain.score.Score;
import bowling.view.InputView;

public class BowlingGame {

    public static void main(String[] args) {
        String name = InputView.scanPlayer();
        Player player = Player.from(name);

        Bowling bowling = Bowling.of(player);

        // TODO : 빈 보드 출력

        while(bowling.isNotEnd()) {
            int frameNumber = bowling.getFrameNumber();
            Score score = Score.of(InputView.scanBowl(frameNumber));

            bowling.bowl(score);

            // TODO : 보드 출력
        }

    }

}
