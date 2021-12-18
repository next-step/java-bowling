package bowling.domain.bowl;

import bowling.domain.pin.Pin;

import java.util.List;

public interface Bowl {

    Bowl pitch(Pin pin);

    boolean canPitch();

    boolean typeEquals(BowlType bowlType);

    List<Pin> pins();
}
