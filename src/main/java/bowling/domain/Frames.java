package bowling.domain;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Frames {
    List<NormalFrame> normalFrames;

    private Frames(List<NormalFrame> normalFrames) {
        this.normalFrames = normalFrames;
    }

    public static final Frames initialize() {
        return new Frames(generateFrameList());
    }

    private static List<NormalFrame> generateFrameList() {
        return IntStream.range(0, 10)
                .mapToObj(index -> new NormalFrame(index))
                .collect(Collectors.toList());
    }

    public boolean isFinish(int round) {
        return frameInRound(round).isFinish();
    }

    private NormalFrame frameInRound(int round) {
        return normalFrames.get(round);
    }

    public void bowl(int round, HitCount hitCount) {
        normalFrames.get(round).bowl(hitCount);
    }
}
