package bowling.domain.frame;

import bowling.domain.pin.PinNo;
import bowling.domain.pin.PinStatus;

import java.util.EnumSet;
import java.util.Objects;
import java.util.Optional;

import static bowling.domain.pin.PinStatus.*;

public class FinalFrame extends Frame {

    private static final EnumSet<PinStatus> EXTRA_AVAILABLE = EnumSet.of(STRIKE, SPARE);

    private PinNo extraNo;

    FinalFrame(int firstNo, int secondNo) {
        super(firstNo, secondNo);
    }

    @Override
    public Frame next(int firstNo, int secondNo) {
        throw new IllegalStateException();
    }

    public Optional<Integer> getExtraNo() {
        return Optional.ofNullable(extraNo)
                .map(PinNo::getNo);
    }

    public Frame addExtra(int extraNo) {
        if (!EXTRA_AVAILABLE.contains(pinNumbers.getStatus())) {
            throw new IllegalStateException("can't add extra number when not strike/spare");
        }
        this.extraNo = PinNo.of(extraNo);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FinalFrame that = (FinalFrame) o;
        return Objects.equals(extraNo, that.extraNo) && Objects.equals(pinNumbers, that.pinNumbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(extraNo, pinNumbers);
    }
}
