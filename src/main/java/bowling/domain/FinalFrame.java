package bowling.domain;

import bowling.type.PlayStatus;

public class FinalFrame extends DefaultFrame{

    public FinalFrame(GeneralScore generalScore, int order) {
        super(generalScore, order);
        this.generalScore = generalScore;
        this.order = order;
        this.playStatus = PlayStatus.IN_PROGRESS;
    }

    @Override
    public Frame nextRound() {
        throw new UnsupportedOperationException("no next round available");
    }

    @Override
    public void nextTry(){
        if (generalScore.size() == 2) {
            generalScore.next(10);
            playStatus = PlayStatus.END;
            return;
        }
        generalScore.next();
        if(generalScore.sum() < 10) {
            playStatus = PlayStatus.END;
        }
    }
}
