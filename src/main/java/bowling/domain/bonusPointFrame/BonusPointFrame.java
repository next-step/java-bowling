package bowling.domain.bonusPointFrame;

import bowling.domain.frame.Frame;

public class BonusPointFrame {

    private int count;
    private Frame frame;

    public BonusPointFrame(int count, Frame frame){
        this.count = count;
        this.frame = frame;
    }
}
