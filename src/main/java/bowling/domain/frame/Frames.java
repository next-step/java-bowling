package bowling.domain.frame;

import bowling.domain.Playable;

import java.util.ArrayList;
import java.util.List;

public class Frames {

    public static final int NORMAL_FRAME_COUNT = 9;
    public static final int TOTAL_FRAME_COUNT = 10;

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
        if(frames.size()<NORMAL_FRAME_COUNT){
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

    public int frameCount() {
        if(frames.isEmpty() || frames.get(frames.size()-1).ended()){
            return frames.size()+1;
        }
        return frames.size();
    }

    public List<Playable> getFrames() {
        return frames;
    }
}
