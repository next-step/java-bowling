package bowling.view;

import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;
import bowling.domain.score.TotalScore;
import bowling.domain.state.*;

/**
 * Created : 2020-12-28 오후 1:38
 * Developer : Seo
 */
public class GridSpare {

    public static int add(Frames frames, TotalScore totalScore, int frameIndex, int playerIndex) {
        Frame frame = frames.get(frameIndex);
        State state = frame.getState(playerIndex);
        int remain = 0;

        if (state instanceof Spare) {
            totalScore.add(state.getScore().getFrameScore());
            addSpareBonus(frames, totalScore, frameIndex, playerIndex);
        }

        if (state instanceof Spare) {
            remain++;
        }

        return remain;
    }

    private static void addSpareBonus(Frames frames, TotalScore totalScore, int frameIndex, int playerIndex) {
        if (frameIndex + Grid.INDEX_ONE < frames.size()) {
            totalScore.add(frames.get(frameIndex + Grid.INDEX_ONE).getFirstScore(playerIndex));
        }
    }

    public static String print(Frames frames, TotalScore totalScore, int frameIndex, int playerIndex) {
        Frame frame = frames.get(frameIndex);
        State state = frame.getState(playerIndex);
        if (state instanceof Spare && frameIndex + Grid.INDEX_ONE < frames.size()) {
            return printSpareQualifiedState(frames, totalScore, frameIndex, playerIndex);
        }
        return Grid.NONE;
    }

    private static String printSpareQualifiedState(Frames frames, TotalScore totalScore, int frameIndex, int playerIndex) {
        Frame nextFrame = frames.get(frameIndex + Grid.INDEX_ONE);
        State nextState = nextFrame.getState(playerIndex);
        if (nextState instanceof Spare || nextState instanceof Miss || nextState instanceof Gutter) {
            return String.format(Grid.FORMAT_SPACE, totalScore.get());
        }
        return Grid.NONE;
    }

    private GridSpare() {
    }
}
