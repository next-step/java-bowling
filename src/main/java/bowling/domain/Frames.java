package bowling.domain;

import bowling.view.ResultView;

import java.util.LinkedList;
import java.util.List;

/**
 * Created : 2020-12-16 오전 8:58
 * Developer : Seo
 */
public class Frames {
    public static final int ALL_FRAMES = 10;
    public static final int START_FRAME = 1;
    public static final int END_FRAME = 11;

    private final List<Frame> frames;

    public Frames(Users users) {
        this.frames = new LinkedList<>();
        frames.add(Frame.init(users));
    }

    public void bowl(ResultView resultView, int frameNo) {
        Frame frame = frames.get(frameNo - 1);
        frames.add(frame.bowl(resultView));
    }

    public int size() {
        return frames.size();
    }

    public List<Frame> getFrames() {
        return frames;
    }

    public Frame get(int i) {
        return frames.get(i);
    }

    public void remove(int i) {
        frames.remove(i);
    }
}
