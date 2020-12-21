package bowling.state;

import bowling.domain.frame.Frame;
import bowling.domain.score.BowlingScore;
import bowling.domain.score.Score;
import bowling.domain.score.ScoreResult;
import bowling.view.ResultView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created By mand2 on 2020-12-21.
 */
public class Spare implements BowlingState {

    private final Frame frame;

    private Spare(Frame frame) {
        this.frame = frame;
    }

    public static BowlingState of(Frame frame) {
        return new Spare(frame);
    }

    @Override
    public boolean isPlayable() {
        return false;
    }

    @Override
    public boolean isFinalPlayable() {
        return true;
    }

    @Override
    public ScoreResult getScoreBoard() {
        return ScoreResult.of(bowlingScores());
    }

    private List<BowlingScore> bowlingScores() {
        List<Integer> list = this.frame.getScore().getList();
        int size = list.size();
        List<BowlingScore> scoreList = new ArrayList<>();

        for (int pin = 0; pin < size; pin++) {
            scoreList.add(BowlingScore.getBowlingScore(list.get(pin), isSecondPitch(pin)));
        }

        return scoreList;
    }

    private boolean isSecondPitch(int index) {
        if (index + 1 == Score.SECOND_PITCH) {
            return true;
        }
        return false;
    }
}

