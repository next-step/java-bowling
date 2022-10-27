package bowling.domain;

import bowling.view.InputView;
import bowling.view.ResultView;

public class FinalFrame extends Frame {

    public void doFrame(FrameHistory frameHistory, Player player) {
        reset();
        frameHistory.addHistory();

        PitchResult pitchResult = null;
        while (!isEndOfFrame()) {
            pitchResult = pitch(frameHistory);
            ResultView.printScoreBoard(frameHistory, player);
        }

        if (checkBonus(pitchResult)) {
            doBonusPitch(frameHistory);
            ResultView.printScoreBoard(frameHistory, player);
        }
    }

    private boolean checkBonus(PitchResult pitchResult) {
        return PitchResult.STRIKE.equals(pitchResult) || PitchResult.SPARE.equals(pitchResult);
    }

    private void doBonusPitch(FrameHistory frameHistory){
        reset();
        int downPinCount = InputView.inputDownPinCount(frameHistory.getLastIndex());
        PitchResult pitchResult = downPin(downPinCount);
        frameHistory.record(pitchResult, downPinCount);
    }

}
