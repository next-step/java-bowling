package bowling.domain.frame;

import java.util.*;
import java.util.stream.Collectors;

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

    public List<NormalFrame> getAll() {
        return Collections.unmodifiableList(normalFrames);
    }

    public NormalFrames add(NormalFrame normalFrame) {
        normalFrames.add(normalFrame);
        return this;
    }

    public NormalFrames findByFrame(int frame) {
        return normalFrames.stream()
                .filter(normalFrame -> normalFrame.isIndex(frame))
                .collect(Collectors.collectingAndThen(Collectors.toList(), NormalFrames::new));
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
