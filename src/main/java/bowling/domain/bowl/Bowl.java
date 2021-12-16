package bowling.domain.bowl;

import bowling.domain.pin.Pin;

public interface Bowl {

    Bowl pitch(Pin pin);

    boolean canPitch();

    boolean typeEquals(BowlType bowlType);

    String getView();
}
