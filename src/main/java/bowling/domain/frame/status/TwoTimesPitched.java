package bowling.domain.frame.status;

import bowling.domain.frame.DownedPin;

public class TwoTimesPitched extends Ended {

    private final DownedPin firstPitch;
    private final DownedPin secondPitch;

    public TwoTimesPitched(DownedPin firstPitch, DownedPin secondPitch) {
        this.firstPitch = firstPitch;
        this.secondPitch = secondPitch;
    }

    @Override
    public String getDescription() {
        return firstPitch.getDescriptionForm(secondPitch);
    }
}
