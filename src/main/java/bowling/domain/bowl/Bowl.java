package bowling.domain.bowl;

import bowling.domain.pin.Pin;

public interface Bowl {

    Bowl pitch(Pin pin);

    boolean canPitch();

    String getView();
}
