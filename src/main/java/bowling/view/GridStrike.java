package bowling.view;

import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;
import bowling.domain.score.TotalScore;
import bowling.domain.state.FinalState;
import bowling.domain.state.Spare;
import bowling.domain.state.State;
import bowling.domain.state.Strike;

/**
 * Created : 2020-12-28 오후 3:45
 * Developer : Seo
 */
public class GridStrike {

    public static int add(Frames frames, TotalScore totalScore, int frameIndex, int playerIndex) {
        if (frameIndex > Grid.MAX_BONUS) {
            return Grid.INDEX_ZERO;
        }
        Frame frame = frames.get(frameIndex);
        State state = frame.getState(playerIndex);
        int remain = 0;

        if (state instanceof Strike) {
            totalScore.add(state.getScore().getFrameScore());
            addStrikeBonus(frames, totalScore, frameIndex, playerIndex);
            remain++;
        }
        // 10프레임 (XX)
        if (state instanceof FinalState && state.getSymbol().length() == Grid.INDEX_TWO) {
            remain++;
        }
        // 10프레임 (XXX)
        if (state instanceof FinalState && state.getSymbol().length() == Grid.INDEX_THREE) {
            remain++;
        }
        // 10프레임 (XX|*)
        if (state instanceof FinalState && state.getSymbol().length() == Grid.INDEX_FOUR) {
            remain++;
        }
        return remain;
    }

    private static void addStrikeBonus(Frames frames, TotalScore totalScore, int frameIndex, int playerIndex) {
        if (frameIndex + Grid.INDEX_ONE < frames.size()) {
            totalScore.add(frames.get(frameIndex + Grid.INDEX_ONE).getFrameScore(playerIndex));
        }

        if (frameIndex + Grid.INDEX_TWO < frames.size()) {
            addNextStrikeBonus(frames, totalScore, frameIndex, playerIndex);
        }
    }

    private static void addNextStrikeBonus(Frames frames, TotalScore totalScore, int frameIndex, int playerIndex) {
        Frame nextFrame = frames.get(frameIndex + Grid.INDEX_ONE);
        State nextState = nextFrame.getState(playerIndex);

        if (nextState instanceof Strike) {
            totalScore.add(frames.get(frameIndex + Grid.INDEX_TWO).getFrameScore(playerIndex));
        }
    }

    public static String print(Frames frames, TotalScore totalScore, int frameIndex, int playerIndex) {
        Frame frame = frames.get(frameIndex);
        State state = frame.getState(playerIndex);

        if (state instanceof Strike) {
            return printStrikeInstance(frames, totalScore, frameIndex, playerIndex);
        }
        // 10프레임
        if (state instanceof FinalState) {
            return printFinal(frames, totalScore, frameIndex, playerIndex);
        }
        return Grid.NONE;
    }

    private static String printFinal(Frames frames, TotalScore totalScore, int frameIndex, int playerIndex) {
        Frame frame = frames.get(frameIndex);
        State state = frame.getState(playerIndex);

        // 10프레임 (X)
        if (state instanceof FinalState && state.getSymbol().length() == Grid.INDEX_ONE) {
            return String.format(Grid.FORMAT_SPACE, totalScore.get());
        }
        // 10프레임 (XX)
        if (state instanceof FinalState && state.getSymbol().length() == Grid.INDEX_TWO) {
            int score = frames.get(frameIndex).getFrameScore(playerIndex);
            if (score == 20) score += 10;
            return String.format(Grid.FORMAT_SPACE, totalScore.get())
                    + String.format(Grid.FORMAT_SPACE, totalScore.addAndGet(score));
        }
        // 10프레임 (XXX)
        if (state instanceof FinalState && state.getSymbol().length() == Grid.INDEX_THREE) {
            int score = frames.get(frameIndex).getFrameScore(playerIndex);
            int score2 = 0;
            if (score == 30) score2 = score + 30;
            return String.format(Grid.FORMAT_SPACE, totalScore.get())
                    + String.format(Grid.FORMAT_SPACE, totalScore.addAndGet(score))
                    + String.format(Grid.FORMAT_SPACE, totalScore.addAndGet(score2));
        }
        // 10프레임 (XX|*)
        if (state instanceof FinalState && state.getSymbol().length() == Grid.INDEX_FOUR) {
            int score = frames.get(frameIndex).getFrameScore(playerIndex);
            int score2 = 0;
            if (score > 20) {
                score2 = score + 30;
                score = 30;
            }
            return String.format(Grid.FORMAT_SPACE, totalScore.get())
                    + String.format(Grid.FORMAT_SPACE, totalScore.addAndGet(score))
                    + String.format(Grid.FORMAT_SPACE, totalScore.addAndGet(score2));
        }
        return Grid.NONE;
    }

    private static String printStrikeInstance(Frames frames, TotalScore totalScore, int frameIndex, int playerIndex) {
        // Strike && Strike
        if (frameIndex + Grid.INDEX_TWO < frames.size()) {
            return printStrikeAndStrike(frames, totalScore, frameIndex, playerIndex);
        }
        // Strike && Spare
        if (frameIndex + Grid.INDEX_ONE < frames.size()) {
            return printStrikeAndSpare(frames, totalScore, frameIndex, playerIndex);
        }
        return String.format(Grid.FORMAT_SPACE, Grid.NONE);
    }

    private static String printStrikeAndStrike(Frames frames, TotalScore totalScore, int frameIndex, int playerIndex) {
        Frame nextNextFrame = frames.get(frameIndex + Grid.INDEX_TWO);
        State nextNextState = nextNextFrame.getState(playerIndex);
        if (nextNextState instanceof Strike) {
            return String.format(Grid.FORMAT_SPACE, totalScore.get());
        }
        return Grid.NONE;
    }

    private static String printStrikeAndSpare(Frames frames, TotalScore totalScore, int frameIndex, int playerIndex) {
        Frame nextFrame = frames.get(frameIndex + Grid.INDEX_ONE);
        State nextState = nextFrame.getState(playerIndex);
        if (nextState instanceof Spare) {
            return String.format(Grid.FORMAT_SPACE, totalScore.get());
        }
        return Grid.NONE;
    }

    private GridStrike() {
    }
}
