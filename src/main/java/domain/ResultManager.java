package domain;

import domain.frames.NormalFrame;

import java.util.ArrayList;
import java.util.List;

public class ResultManager {
    private List<NormalFrame> normalFrames;

    public ResultManager() {
        normalFrames = new ArrayList<>();
    }

    public void addResult(NormalFrame normalFrame) {
        normalFrames.add(normalFrame);
    }

    public int getFrameNumber(int number) {
        return this.normalFrames.get(number).getFrameNumber();
    }


    public String toString(int number) {
        return this.normalFrames.get(number).toString();
    }

    public List<NormalFrame> getNormalFrames() {
        return normalFrames;
    }
}
