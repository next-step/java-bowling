package bowling.view;

import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;
import bowling.domain.score.TotalScore;
import bowling.domain.state.Miss;
import bowling.domain.state.State;

/**
 * Created : 2020-12-28 오후 1:33
 * Developer : Seo
 */
public class GridMiss {

    public static int add(Frames frames, TotalScore totalScore, int frameIndex, int userIndex) {
        Frame frame = frames.get();
        State state = frame.getState();
        int remain = 0;

        if (state instanceof Miss) {
            totalScore.add(state.getScore().getFrameScore());
        }
        if (state instanceof Miss && !state.isFinished()) {
            remain++;
        }

        return remain;
    }

    public static String print(Frames frames, TotalScore totalScore, int frameIndex, int playerIndex) {
        Frame frame = frames.get();
        State state = frame.getState();
        if (state instanceof Miss && state.isFinished()) {
            return String.format(Grid.FORMAT_SPACE, totalScore.get());
        }
        return Grid.NONE;
    }

    private GridMiss() {
    }
}
