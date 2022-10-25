package bowling.domain;


import bowling.view.InputView;

public class Frame {
    private int restPin = RuleConfig.NUMBER_OF_PIN;
    private int restPitchCount = RuleConfig.PITCH_COUNT;

    private void reset(){
        restPin = RuleConfig.NUMBER_OF_PIN;
        restPitchCount = RuleConfig.PITCH_COUNT;
    }

    public void doFrame(FrameHistory frameHistory, Player player) {
        reset();
        frameHistory.addHistory();

        while (!isEndOfFrame()) {
            PitchResultEnum pitch = pitch(frameHistory);

            // TODO
            // 볼링 점수판 출력
        }
    }

    private PitchResultEnum pitch(FrameHistory frameHistory) {
        int downPinCount = InputView.inputDownPinCount(frameHistory.getLastIndex());

        PitchResultEnum pitchResultEnum = downPin(downPinCount);

        frameHistory.record(pitchResultEnum, downPinCount);

        return pitchResultEnum;
    }

    private boolean isEndOfFrame() {
        return isDownAllPin() || isTryOut();
    }


    private PitchResultEnum downPin(int downPinCount){
        restPin -= downPinCount;
        restPitchCount--;

        return PitchResultEnum.getPitchResultEnum(restPin, downPinCount, restPitchCount);
    }

    private boolean isDownAllPin() {
        return this.restPin <= 0;
    }

    private boolean isTryOut() {
        return this.restPitchCount <= 0;
    }
}