package bowling.view;

import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;
import bowling.domain.score.TotalScore;
import bowling.domain.state.Gutter;
import bowling.domain.state.State;

/**
 * Created : 2020-12-28 오후 1:27
 * Developer : Seo
 */
public class GridGutter {

    public static int add(Frames frames, TotalScore totalScore, int frameIndex, int playerIndex) {
        Frame frame = frames.get(frameIndex);
        State state = frame.getState(playerIndex);
        int remain = 0;

        if (state instanceof Gutter && !state.isFinished()) {
            remain++;
        }

        return remain;
    }

    public static String print(Frames frames, TotalScore totalScore, int frameIndex, int playerIndex) {
        Frame frame = frames.get(frameIndex);
        State state = frame.getState(playerIndex);
        if (state instanceof Gutter && state.isFinished()) {
            return String.format(Grid.FORMAT_SPACE, totalScore.get());
        }
        return Grid.NONE;
    }

    private GridGutter() {
    }
}
