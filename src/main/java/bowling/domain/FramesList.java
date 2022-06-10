package bowling.domain;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FramesList {
    private final List<Frames> userFrames;

    public FramesList(Users users) {
        this.userFrames = IntStream.range(0, users.size())
                .mapToObj(i -> new Frames(new NormalFrame(1)))
                .collect(Collectors.toList());
    }

    public Frame getUserFrame(int index, int frameIndex) {
        return this.userFrames.get(index).getFrame(frameIndex);
    }

    public void addNextFrame(int index, Frame nextFrame) {
        this.userFrames.get(index).add(nextFrame);
    }

    public List<Integer> calculateTotalScore(int index, int round) {
        return this.userFrames.get(index).calculateTotalScore(round);
    }

    public boolean isFramesFinish(int i, int round) {
        return this.userFrames.get(i).getFrame(round).isFinish();
    }

    public Frame getFrame(int i, int round) {
        return this.userFrames.get(i).getFrame(round);
    }

}
