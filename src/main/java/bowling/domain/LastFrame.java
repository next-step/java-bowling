package bowling.domain;

import bowling.view.InputView;
import bowling.view.ResultView;

public class LastFrame extends Frame {

    public void doFrame(FrameHistory frameHistory, Player player) {
        reset();
        frameHistory.addHistory();

        PitchResultEnum pitchResultEnum = null;
        while (!isEndOfFrame()) {
            pitchResultEnum = pitch(frameHistory);

            // TODO
            // 볼링 점수판 출력
        }

        if(checkBonus(pitchResultEnum)){
            doBonusPitch(frameHistory);

            // TODO
            // 볼링 점수판 출력
        }
    }

    private boolean checkBonus(PitchResultEnum pitchResultEnum){
        return PitchResultEnum.STRIKE.equals(pitchResultEnum) || PitchResultEnum.SPARE.equals(pitchResultEnum);
    }

    private void doBonusPitch(FrameHistory frameHistory){
        reset();
        int downPinCount = InputView.inputDownPinCount(frameHistory.getLastIndex());
        PitchResultEnum pitchResultEnum = downPin(downPinCount);
        frameHistory.record(pitchResultEnum, downPinCount);
    }

}
