package bowling.domain.frame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class NormalFrames {

    private final List<NormalFrame> normalFrames;

    private NormalFrames() {
        this.normalFrames = new ArrayList<>();
    }

    private NormalFrames(List<NormalFrame> normalFrames) {
        this.normalFrames = normalFrames;
    }

    public static NormalFrames empty() {
        return new NormalFrames();
    }

    public static NormalFrames of(NormalFrame... normalFrames) {
        return new NormalFrames(Arrays.asList(normalFrames));
    }

    public NormalFrames add(NormalFrame normalFrame) {
        normalFrames.add(normalFrame);
        return this;
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
}
