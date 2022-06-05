package bowling.domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FramesList {
    private List<Frames> userFrames;

    public FramesList(Users users) {
        this.userFrames = IntStream.range(0, users.size())
                .mapToObj(i -> new Frames(new NormalFrame(1)))
                .collect(Collectors.toList());
    }

    public List<Frames> getUserFrames() {
        return Collections.unmodifiableList(this.userFrames);
    }

    public boolean isFramesFinish(int i, int round) {
        return userFrames.get(i).getFrame(round).isFinish();
    }

    public boolean isFinishAllFromFrame(int i) {
        long count = userFrames.stream()
                .filter(frames -> frames.getFrame(i).isFinish())
                .count();

        return count == userFrames.size();
    }

}
