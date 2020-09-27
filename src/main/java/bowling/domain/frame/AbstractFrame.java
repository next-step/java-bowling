package bowling.domain.frame;

import bowling.domain.Swing;
import bowling.domain.SwingRecord;

public abstract class AbstractFrame implements Frame {

    private final SwingRecord swingRecord;
    private final Swing swing;

    public AbstractFrame() {
        swingRecord = new SwingRecord();
        swing = new Swing();
    }

    @Override
    public void swing(int score) {
        swing.addScore(score);
        swingRecord.addHistory(score);
    }

    @Override
    public String swingRecordsToString() {
        return swingRecord.toString();
    }

    Swing getSwing() {
        return swing;
    }
}
