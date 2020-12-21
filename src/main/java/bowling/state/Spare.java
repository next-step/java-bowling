package bowling.state;

import bowling.domain.frame.Frame;
import bowling.domain.score.BowlingScore;
import bowling.domain.score.Score;
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
    public boolean isSpare() {
        return true;
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
    public String printResult() {
        List<String> buffer = new ArrayList<>();
        List<Integer> list = this.frame.getScore().getList();
        for (int pitch = 0; pitch < list.size(); pitch++) {
            buffer.add(BowlingScore.getResultScore(list.get(pitch), isSecondPitch(pitch)));
        }

        return String.join(ResultView.DELIMITER, buffer);
    }

    private boolean isSecondPitch(int index) {
        if (index + 1 == Score.SECOND_PITCH) {
            return true;
        }
        return false;
    }

}

