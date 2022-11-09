package bowling.step2;

import bowling.step2.domain.Player;
import bowling.step2.domain.Score;
import bowling.step2.domain.Scores;
import bowling.step2.utils.GameRuleUtils;
import bowling.step2.view.InputView;
import bowling.step2.view.ResultView;

import java.util.ArrayList;
import java.util.List;


public class BowlingGameMain {

    public static void main(String[] args) {
        Player player = new Player(InputView.inputPlayerName());
        ResultView.printInitScoreBoard(player);
        for (int i = GameRuleUtils.GAME_START_INDEX; i <= GameRuleUtils.GAME_LAST_INDEX; i++) {
            player.addScoreMap(i, playGame(i));
            ResultView.printGameScoreBoard(player);
        }
    }

    public static Scores playGame(int index) {
        int count = 0;
        int frameCount = GameRuleUtils.PER_FRAME_COUNT;
        List<Score> scores = new ArrayList<>();

        while (count < frameCount) {

            Score score = new Score(InputView.inputScore(index));
            scores.add(score);
            count++;

            if (index != GameRuleUtils.GAME_LAST_INDEX && score.isStrike()) {
                break;
            }

            if (index == GameRuleUtils.GAME_LAST_INDEX && count == GameRuleUtils.PER_FRAME_COUNT
                    && isContainStrikeOrSpare(scores)) {
                frameCount += GameRuleUtils.BONUS_COUNT;
            }
        }
        return new Scores(scores);
    }

    private static boolean isContainStrikeOrSpare(List<Score> scores) {
        return scores.stream().anyMatch(it -> it.isStrike() || it.isSpare());
    }
}
