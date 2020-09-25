package bowling.domain.frame;

import java.util.Objects;

import bowling.domain.core.RolledResult;
import bowling.domain.core.state.Score;

final class LinkedScoreCalculator {
    private Frame prevFrame;
    private Frame nextFrame;

    LinkedScoreCalculator(Frame prevFrame, Frame nextFrame) {
        this.prevFrame = prevFrame;
        this.nextFrame = nextFrame;
    }

    private int getPrevFrameScore(){
        if (Objects.nonNull(prevFrame)) {
            return prevFrame.getScore();
        }
        return 0;
    }

    int getScore(RolledResult rolledResult) {
        Score score = getScoreNew(rolledResult);
        if (score.hasNotAttemptLeft()){
            return getPrevFrameScore() + score.getScore();
        }
        return Score.NOT_CALCULATED;
    }

    Score getScoreNew(RolledResult rolledResult) {
        Score score = rolledResult.getScore();
        if (score.hasNotAttemptLeft()) {
            return score;
        }
        return nextFrame.calculateScore(score);
    }

    Score calculateScore(RolledResult rolledResult, Score beforeScore) {
        Score score = rolledResult.calculateScore(beforeScore);
        if (score.hasNotAttemptLeft()) {
            return score;
        }
        return nextFrame.calculateScore(score);
    }
}
