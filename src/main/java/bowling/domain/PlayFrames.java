package bowling.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlayFrames {
    private final List<PlayFrame> playFrames;

    public PlayFrames() {
        this.playFrames = Collections.unmodifiableList(new ArrayList<>());
    }

    public void add(PlayFrame playFrame) {
        this.playFrames.add(playFrame);
    }

    public List<PlayFrame> playFrames() {
        return this.playFrames;
    }
}
