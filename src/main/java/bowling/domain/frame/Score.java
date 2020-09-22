package bowling.domain.frame;

import java.util.Objects;

import bowling.domain.core.RolledResult;

class Score {
    private Frame prevFrame;
    private Frame nextFrame;

    Score(Frame prevFrame, Frame nextFrame) {
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
        if (rolledResult.isCompleteState()) {
            return getPrevFrameScore() + rolledResult.getRolledResultScore()
                + rolledResult.getNextRolledResultMergeScore(nextFrame.getRolledResult())
                ;
        }
        return 0;
    }
}
