package bowling.domain.frame;

import java.util.ArrayList;
import java.util.List;

public class MultiFrames {
    private final List<Frames> multiFrames;

    public MultiFrames(List<Frames> multiFrames) {
        this.multiFrames = multiFrames;
    }

    public static MultiFrames init(int countOfPlayer) {
        List<Frames> multiFrames = new ArrayList<>();
        for (int i = 0; i < countOfPlayer; i++) {
            multiFrames.add(Frames.init());
        }
        return new MultiFrames(multiFrames);
    }

    public boolean isOver() {
        return getLastPlayerFrames().isOver();
    }

    private Frames getLastPlayerFrames() {
        return multiFrames.get(multiFrames.size() - 1);
    }

    public Frames getPlayerFrame(int idx) {
        return multiFrames.get(idx);
    }

    public void addPlayerNextFrame(int i, Frame frame) {
        Frames frames = multiFrames.get(i).addNextFrame(frame);
        multiFrames.set(i, frames);
    }
}
