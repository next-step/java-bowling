package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class NormalFrames {
    private List<NormalFrame> normalFrames = new ArrayList<>();

    public NormalFrames(List<NormalFrame> normalFrames) {
        this.normalFrames = normalFrames;
    }

    public int size() {
        return normalFrames.size();
    }
}
