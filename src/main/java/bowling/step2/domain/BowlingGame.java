package bowling.step2.domain;

import bowling.step2.view.InputView;

import java.util.ArrayList;
import java.util.List;
public class BowlingGame {
    public static final int GAME_START_INDEX = 1;
    public static final int GAME_LAST_INDEX = 10;
    private static final int BONUS_COUNT = 1;
    private static final int PER_FRAME_COUNT = 2;


    public static Scores playGame(int index) {
        int count = 0;
        int frameCount = PER_FRAME_COUNT;
        List<Score> scores = new ArrayList<>();

        while (count < frameCount) {

            Score score = new Score(InputView.inputScore(index));
            scores.add(score);
            count++;

            if (index != GAME_LAST_INDEX && score.isStrike()) {
                break;
            }

            if (index == GAME_LAST_INDEX && count == PER_FRAME_COUNT
                    && isContainStrikeOrSpare(scores)) {
                frameCount += BONUS_COUNT;
            }
        }
        return new Scores(scores);
    }

    private static boolean isContainStrikeOrSpare(List<Score> scores) {
        return scores.stream().anyMatch(it -> it.isStrike() || it.isSpare());
    }

}
