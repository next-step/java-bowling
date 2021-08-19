package bowling.domain.frame;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Frames {

    private static final int MAX = 10;

    private final List<NormalFrame> normalFrames;

    private Frames(final List<NormalFrame> normalFrames) {
        this.normalFrames = normalFrames;
    }

    public static Frames of() {
        return new Frames(Stream.of(NormalFrame.of())
            .collect(Collectors.toList()));
    }

    public boolean isEnd() {
        return normalFrames.size() == MAX && getLast().isEnd();
    }

    public int current() {
        if (getLast().isEnd()) {
            return normalFrames.size() + 1;
        }

        return normalFrames.size();
    }

    public NormalFrame getLast() {
        return normalFrames.get(normalFrames.size() - 1);
    }

    public void pitch(final PitchResult result) {
        final NormalFrame lastNormalFrame = getLast();

        if (lastNormalFrame.isEnd()) {
            final NormalFrame newNormalFrame = NormalFrame.of();
            newNormalFrame.pitch(result);
            normalFrames.add(newNormalFrame);
            return;
        }

        lastNormalFrame.pitch(result);
    }

    public List<NormalFrame> getFrames() {
        return Collections.unmodifiableList(normalFrames);
    }
}
