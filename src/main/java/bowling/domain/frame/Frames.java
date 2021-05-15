package bowling.domain.frame;

import bowling.domain.Point;

import java.util.List;

public class Frames {

    private int frameCount;
    private List<Frame> frames;

    public void bowl(Point point) {
        Frame currentFrame = frames.get(frameCount);
        currentFrame.bowl(point);
    }

    public boolean isEnd() {
        return true;
    }

}
