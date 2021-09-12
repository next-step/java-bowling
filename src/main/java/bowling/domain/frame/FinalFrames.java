package bowling.domain.frame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class FinalFrames {

    private final List<FinalFrame> finalFrames;

    private FinalFrames() {
        this.finalFrames = new ArrayList<>();
    }

    public static FinalFrames empty() {
        return new FinalFrames();
    }

    public FinalFrames add(FinalFrame finalFrame) {
        finalFrames.add(finalFrame);
        return this;
    }

    public List<FinalFrame> getAll() {
        return Collections.unmodifiableList(finalFrames);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FinalFrames that = (FinalFrames) o;
        return Objects.equals(finalFrames, that.finalFrames);
    }

    @Override
    public int hashCode() {
        return Objects.hash(finalFrames);
    }
}
