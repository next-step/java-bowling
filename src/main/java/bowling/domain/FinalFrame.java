package bowling.domain;

import bowling.view.InputView;
import bowling.view.ResultView;

public class FinalFrame extends Frame {

    public void doFrame(FrameHistory frameHistory, Player player) {
        reset();
        frameHistory.addHistory();

        PitchResultEnum pitchResultEnum = null;
        while (!isEndOfFrame()) {
            pitchResultEnum = pitch(frameHistory);
            ResultView.printScoreBoard(frameHistory, player);
        }

        if(checkBonus(pitchResultEnum)){
            doBonusPitch(frameHistory);
            ResultView.printScoreBoard(frameHistory, player);
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
