package bowling.step2.domain.frame;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Frames {

    private static final int INIT_NUMBER = 1;
    private static final int ONE = 1;
    private static final String FRAME_FORMAT = "%-4s";
    private static final String FRAME_DELIMITER = "|  ";

    List<Frame> frames;

    public Frames() {
        this.frames = new ArrayList<>();
        frames.add(new NormalFrame(INIT_NUMBER));
    }

    public Frames(List<Frame> frames){
        this.frames = frames;
    }

    public void progress(int pitch) {
        Frame frame = frames.get(frameIndex()).pitch(pitch);

        if(frame.pitchesOver()){
            nextFrame(frame);
        }
    }

    private void nextFrame(Frame frame) {
        frames.set(frameIndex(), frame);
        frames.add(frame.nextFrame());
    }

    private int frameIndex() {
        return currentFrameNo() - ONE;
    }

    public int currentFrameNo(){
        return frames.size();
    }

    public boolean isEnd() {
        return frames.stream()
                     .anyMatch(frame -> frame == null);
    }

    public int getFramesSize(){
        return frames.size();
    }

    @Override
    public String toString() {
        return frames.stream()
                     .filter(frame -> frame != null)
                     .map(frame -> String.format(FRAME_FORMAT, frame.toString()))
                     .collect(Collectors.joining(FRAME_DELIMITER));
    }
}
