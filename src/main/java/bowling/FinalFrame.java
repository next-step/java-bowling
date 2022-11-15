package bowling;

public class FinalFrame extends Frame {

    @Override
    public boolean strikeCondition() {
        return (!hitRecords.hitTimes(HIT_TWICE) || hitRecords.isRecordAllStrike()) && bowlingPin.isZero();
    }

    @Override
    public boolean finishFrame() {
        boolean result = hitRecords.hitTimes(HIT_TRIPLE) || failedBounsFrame();
        if (clearAllFrame()) {
            chargeBowlingPin();
        }
        return result;
    }

    private boolean failedBounsFrame() {
        return hitRecords.hitTimes(HIT_TWICE) && !clearAllFrame();
    }
}
