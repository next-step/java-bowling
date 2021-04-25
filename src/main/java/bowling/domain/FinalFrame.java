package bowling.domain;

public class FinalFrame extends Frame {

    private Pitch bonusPitch;

    protected FinalFrame(int number, Frame before) {
        super(number, before);
    }

    @Override
    public void pitch(Pitch pitch) {
        if (pitches().isStrike() || pitches().isSpare()) {
            this.bonusPitch = pitch;
            return;
        }
        pitches().add(pitch);
    }

    @Override
    public boolean isFinished() {
        return this.bonusPitch != null || pitches().isMiss();
    }

    @Override
    public String printScoreBoard() {
        return null;
    }

}
