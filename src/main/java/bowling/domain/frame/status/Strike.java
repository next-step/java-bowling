package bowling.domain.frame.status;

import bowling.domain.frame.DownedPin;

public class Strike extends Ended {

    private final DownedPin firstPitch;

    public Strike() {
        firstPitch = DownedPin.fromNumber(10);
    }

    @Override
    public String getDescription() {
        return firstPitch.getDescriptionForm();
    }
}
