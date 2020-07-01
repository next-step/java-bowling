package bowling.domain.frame;

import bowling.domain.Score;

import java.util.ArrayList;
import java.util.List;

public class Frame {

    protected static final int LIMIT_COUNT = 2;

    List<Score> scores = new ArrayList<>();

    public int addScore(int score, Frames frames) {
        scores.add(new Score(score, scores));
        return moveNextFrame(frames);
    }

    public Frame makeFrame(int frameSize) {
        if(frameSize == Frames.BOWLING_GAME_FRAME){
            return new FinalFrame();
        }
        return new NormalFrame();
    }

    public boolean isFirst() {
        return scores.size() == 0;
    }

    public boolean isStrike() {
        return scores.get((0)).validateMaxScore();
    }

    public boolean isMiss() {
        return scores.get((scores.size() - 1)).validateMinScore();
    }

    public boolean isSpare() {
        if(scores.size() < LIMIT_COUNT) {
            return false;
        }
        return scores.get(0).getScore()
                + scores.get(1).getScore()
                == Score.MAX_SCORE;
    }

    public int getFrameLastScore() {
        return scores.get((scores.size() - 1)).getScore();
    }

    public int getFrameTotalScore() {
        return scores.stream()
                .mapToInt(Score::getScore)
                .sum();
    }

    public int getScore(Frames frames) {
        int totalScore = getFrameTotalScore();
        if (isSpare() || isStrike()) {
            totalScore += getNextScore(frames, this);
        }

        return totalScore;
    }

    private int getNextScore(Frames frames, Frame frame) {
        int score = 0;
        int nextFrameIndex = frames.getFrames().indexOf(frame) + 1;
        Frame nextFrame = frames.getFrames().get(nextFrameIndex);

        if(nextFrame.isFirst()) {
            return score;
        }

        if(nextFrame.isStrike()) {
            //score += getNextScore(frames, nextFrame);
        }

        score += nextFrame.scores.get(0).getScore();
        return score;
    }

    public int moveNextFrame(Frames frames) {
        return 1;
    }
}
