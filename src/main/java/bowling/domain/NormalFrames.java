package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class NormalFrames {

    public static final int NORMALFRAME_NUMBER = 9;

    private List<NormalFrame> normalFrames;

    private NormalFrames(List<NormalFrame> normalFrames) {
        this.normalFrames = normalFrames;
    }

    public static NormalFrames init() {
        List<NormalFrame> normalFrames = new ArrayList<>(NORMALFRAME_NUMBER);
        return new NormalFrames(normalFrames);
    }

    public NormalFrames add(NormalFrame normalFrame) {
        normalFrames.add(normalFrame);
        return new NormalFrames(normalFrames);
    }

    public int getNormalFrameSize() {
        return normalFrames.size();
    }

}
