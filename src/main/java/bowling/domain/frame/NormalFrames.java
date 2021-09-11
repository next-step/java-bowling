package bowling.domain.frame;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class NormalFrames {

    private final List<NormalFrame> normalFrames;

    private NormalFrames() {
        this.normalFrames = new ArrayList<>();
    }

    public static NormalFrames empty() {
        return new NormalFrames();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NormalFrames that = (NormalFrames) o;
        return Objects.equals(normalFrames, that.normalFrames);
    }

    @Override
    public int hashCode() {
        return Objects.hash(normalFrames);
    }

    public NormalFrames add(NormalFrame normalFrame) {
        normalFrames.add(normalFrame);
        return this;
    }
}
