package bowling.domain.frame;

import bowling.domain.Playable;

import java.util.ArrayList;
import java.util.List;

public class Frames {

    private static final int NORMAL_FRAME_COUNT = 9;
    private static final int TOTAL_FRAME_COUNT = 10;

    private List<Playable> frames;

    public Frames() {
        this.frames = new ArrayList<>();
    }

    public void throwBall(int point) {
        AddFrameIfNeeded();
        frames.get(frames.size()-1)
                .throwBall(point);
    }

    private void AddFrameIfNeeded() {
        if(frames.isEmpty() || frames.get(frames.size()-1).ended()){
            addFrame();
        }
    }

    private void addFrame() {
        if(frames.size()<9){
            frames.add(new Frame());
            return;
        }
        frames.add(new FinalFrame());
    }

    public boolean ended() {
        if (frames.size()<TOTAL_FRAME_COUNT) {
            return false;
        }
        return frames.get(numberToIndex(TOTAL_FRAME_COUNT)).ended();
    }

    private int numberToIndex(int number) {
        return number - 1;
    }

    private int indexToNumber(int number) {
        return number + 1;
    }

    public int frameCount() {
        return indexToNumber(frames.size());
    }

    public List<Playable> getFrames() {
        return frames;
    }
}
