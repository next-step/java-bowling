package bowling.bowlingplayers.domain.frame;

import bowling.bowlingplayers.domain.pitching.Pitching;

import java.util.Objects;

public class FinalFrame extends Frame {

    private Pitching bonusPitching1;
    private Pitching bonusPitching2;

    public FinalFrame(Pitching firstPitching) {
        super(firstPitching);
    }

    public FinalFrame(Pitching firstPitching, Frame beforeFrame) {
        super(firstPitching, beforeFrame);
    }

    @Override
    public void pitch(Pitching pitching) {
        if (strike() || spare()) {
            bonusPitch(pitching);
            return;
        }
        super.pitch(pitching);
    }

    private void bonusPitch(Pitching bonusPitching) {
        if (bonusPitching1 == null) {
            bonusPitching1 = bonusPitching;
            return;
        }
        bonusPitching2 = bonusPitching;
    }

    @Override
    public boolean done() {
        if(strike()) {
            return bonusPitching2 != null;
        }
        if(spare()) {
            return bonusPitching1 != null;
        }
        return !(this.secondScore() == Pitching.IS_NULL);
    }

    public Integer firstBonusScore() {
        if (bonusPitching1 == null) {
            return Pitching.IS_NULL;
        }
        return bonusPitching1.pins();
    }

    public Integer secondBonusScore() {
        if (bonusPitching2 == null) {
            return Pitching.IS_NULL;
        }
        return bonusPitching2.pins();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FinalFrame)) return false;
        if (!super.equals(o)) return false;
        FinalFrame that = (FinalFrame) o;
        return Objects.equals(bonusPitching1, that.bonusPitching1) && Objects.equals(bonusPitching2, that.bonusPitching2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), bonusPitching1, bonusPitching2);
    }
}
