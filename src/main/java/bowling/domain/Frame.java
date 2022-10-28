package bowling.domain;


import bowling.view.InputView;
import bowling.view.ResultView;

public class Frame {
    private final Scores scores;

    public Frame() {
        this.scores = new Scores();
    }

    public void doFrame(FrameHistory frameHistory, Player player) {
        reset();
        frameHistory.addHistory();

        while (!isEndOfFrame()) {
            pitch(frameHistory);
            ResultView.printScoreBoard(frameHistory, player);
        }
    }

    protected PitchResult pitch(FrameHistory frameHistory) {
        int downPinCount = InputView.inputDownPinCount(frameHistory.getLastIndex());

        PitchResult pitchResult = downPin(downPinCount);

        frameHistory.record(pitchResult, downPinCount);

        return pitchResult;
    }

    protected boolean isEndOfFrame() {
        return isDownAllPin() || isTryOut();
    }


    protected PitchResult downPin(int downPinCount) {
        checkDownPinCount(downPinCount);

        restPin -= downPinCount;
        restPitchCount--;

        return PitchResult.of(restPin, downPinCount, restPitchCount);
    }

    private boolean isDownAllPin() {
        return this.restPin <= 0;
    }

    private boolean isTryOut() {
        return this.restPitchCount <= 0;
    }

    private void checkDownPinCount(int downPinCount){
        if(restPin < downPinCount){
            throw new IllegalArgumentException(WRONG_INPUT_STRING);
        }
    }
}