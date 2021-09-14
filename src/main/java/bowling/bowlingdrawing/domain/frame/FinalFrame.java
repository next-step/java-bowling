package bowling.bowlingdrawing.domain.frame;

import bowling.bowlingdrawing.domain.pitching.Pitching;

import java.util.Objects;

public class FinalFrame{

    private final Frame finalFrame;
    private Pitching bonusPitching1;
    private Pitching bonusPitching2;

    public FinalFrame(Pitching pitching) {
        this.finalFrame = new Frame(pitching);
    }

    public void secondPitching(Pitching pitching2) {
    }

    public void bonusPitch(Pitching pitching2) {
    }

    public boolean end() {
        return false;
    }

    public boolean strike() {
        return false;
    }

    public boolean spare() {
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FinalFrame)) return false;
        FinalFrame that = (FinalFrame) o;
        return Objects.equals(finalFrame, that.finalFrame) && Objects.equals(bonusPitching1, that.bonusPitching1) && Objects.equals(bonusPitching2, that.bonusPitching2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(finalFrame, bonusPitching1, bonusPitching2);
    }
}
