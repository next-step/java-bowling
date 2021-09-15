package bowling.bowlingdrawing.domain.frame;

import bowling.bowlingdrawing.domain.pitching.Pitching;

public class NormalFrame extends Frame {

    public NormalFrame(Pitching firstPitching, Pitching secondPitching) {
        super(firstPitching, secondPitching);
    }

    public NormalFrame(Pitching firstPitching) {
        super(firstPitching);
    }


}
