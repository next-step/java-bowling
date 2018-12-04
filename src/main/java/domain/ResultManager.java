package domain;

import domain.frames.Frame;
import domain.frames.NormalFrame;

import java.util.ArrayList;
import java.util.List;

public class ResultManager {
    private List<Frame> normalFrames;

    public ResultManager() {
        normalFrames = new ArrayList<>();
    }

    public void addResult(Frame normalFrame) {
        normalFrames.add(normalFrame);
    }

    public int getFrameNumber(int number) {
        return this.normalFrames.get(number).getFrameNumber();
    }

    public List<Frame> getNormalFrames() {
        return normalFrames;
    }

    public void deleteLastResult() {
        normalFrames.remove(normalFrames.size()-1);
    }
}
