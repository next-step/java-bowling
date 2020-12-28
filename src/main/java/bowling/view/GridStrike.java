package bowling.view;

import bowling.domain.Symbol;
import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;
import bowling.domain.score.TotalScore;
import bowling.domain.state.*;

/**
 * Created : 2020-12-28 오후 3:45
 * Developer : Seo
 */
public class GridStrike {

    public static int add(Frames frames, TotalScore totalScore, int frameIndex, int playerIndex) {
        Frame frame = frames.get(frameIndex);
        State state = frame.getState(playerIndex);
        int remain = Grid.INDEX_ZERO;

        if (state instanceof Strike) {
            totalScore.add(state.getScore().getFrameScore());
            remain++;
            remain += addStrikeBonus(frames, totalScore, frameIndex, playerIndex);
        }
        if (state instanceof FinalState) {
            totalScore.add(addFinalScore(state));
        }
        return remain;
    }

    private static int addFinalScore(State state) {
        int ret = 0;
        if ("XXX".equals(state.getSymbol())) {
            ret = 20;
        }
        return ret;
    }

    private static int addStrikeBonus(Frames frames, TotalScore totalScore, int frameIndex, int playerIndex) {
        int remain = Grid.INDEX_ZERO;
        // 8프레임일 때 10프레임 값
        if (frameIndex == Grid.NORMAL_FRAME_MAX) {
        }
        if (frameIndex + Grid.INDEX_ONE < frames.size()) {
            totalScore.add(frames.get(frameIndex + Grid.INDEX_ONE).getFrameScore(playerIndex));
            remain++;
        }
        if (frameIndex + Grid.INDEX_TWO < frames.size()) {
            remain += addNextStrikeBonus(frames, totalScore, frameIndex, playerIndex);
        }
        if (frameIndex == Grid.NORMAL_FRAME_MAX) remain--;
        return remain;
    }

    private static int addNextStrikeBonus(Frames frames, TotalScore totalScore, int frameIndex, int playerIndex) {
        Frame nextFrame = frames.get(frameIndex + Grid.INDEX_ONE);
        State nextState = nextFrame.getState(playerIndex);
        int remain = Grid.INDEX_ZERO;

        if (nextState instanceof Strike && frameIndex == 7) {
            totalScore.add(Symbol.STRIKE.getPins().get());
            remain += Grid.INDEX_TWO;
            return remain;
        }
        if (nextState instanceof Strike) {
            totalScore.add(frames.get(frameIndex + Grid.INDEX_TWO).getFrameScore(playerIndex));
            remain++;
        }
        return remain;
    }

    public static String print(Frames frames, TotalScore totalScore, int frameIndex, int playerIndex) {
        Frame frame = frames.get(frameIndex);
        State state = frame.getState(playerIndex);

        if (state instanceof Strike) {
            return printStrike(frames, totalScore, frameIndex, playerIndex);
        }
        if (state instanceof FinalState && frameIndex > 8) {
            return printFinal(frames, totalScore, frameIndex, playerIndex);
        }
        return Grid.NONE;
    }

    private static String printStrike(Frames frames, TotalScore totalScore, int frameIndex, int playerIndex) {
        if (frameIndex + Grid.INDEX_TWO < frames.size()) {
            return String.format(Grid.FORMAT_SPACE, totalScore.get());
        }
        if (frameIndex + Grid.INDEX_ONE < frames.size() && frameIndex < Grid.NORMAL_FRAME_MAX) {
            return String.format(Grid.FORMAT_SPACE, Grid.NONE);
        }
        if (frameIndex == Grid.NORMAL_FRAME_MAX && frames.size() == 9) {
            return String.format(Grid.FORMAT_SPACE, Grid.NONE);
        }

        return Grid.NONE;
    }

    private static String printFinal(Frames frames, TotalScore totalScore, int frameIndex, int playerIndex) {
        Frame frame = frames.get(frameIndex);
        State state = frame.getState(playerIndex);

        StringBuilder sb = new StringBuilder();
        if (!state.isFinished()) {
            sb.append(String.format(Grid.FORMAT_SPACE, Grid.NONE));
            sb.append(String.format(Grid.FORMAT_SPACE, Grid.NONE));
        }
        if (state.isFinished()) {
            sb.append(String.format(Grid.FORMAT_SPACE, Integer.parseInt(totalScore.get()) - state.getScore().getFrameScore()));
            sb.append(String.format(Grid.FORMAT_SPACE, totalScore.get()));
        }

        return sb.toString();
    }

    private GridStrike() {
    }
}
