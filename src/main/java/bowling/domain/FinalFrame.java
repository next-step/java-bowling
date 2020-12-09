package bowling.domain;

public class FinalFrame extends Frame{
    public FinalFrame(final int index) {
        super(index);
    }

    @Override
    public void pitch(Pins pins) {
        
    }

    @Override
    public boolean isPitchable() {
        return false;
    }
}
