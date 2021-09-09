package bowling.domain.frame;

import java.util.*;

public class Frames {
    private static final int FINAL_FRAME_NO = 10;

    private List<NormalFrame> normalFrames = new ArrayList<>();
    private List<FinalFrame> finalFrames = new ArrayList<>();

    public Frames() {
        this(1);
    }

    public Frames(int frameNo) {
        if(frameNo < FINAL_FRAME_NO){
            this.normalFrames.add(new NormalFrame(frameNo));
            return;
        }
        this.finalFrames.add(new FinalFrame(frameNo));
    }

    public Frames next(int number) {
        if(currentFrameNo() > FINAL_FRAME_NO) {
            throw new IllegalStateException("마지막 프레임의 투구를 모두 마쳤습니다.");
        }

        if(currentFrameNo() == FINAL_FRAME_NO){
            setFinalFrames(number);
            return this;
        }

        setNormalFrames(number);
        return this;
    }

    private void setNormalFrames(int number) {
        NormalFrame current = lastNormalFrame();
        NormalFrame next = current.next(number);
        if (current != next) {
            this.normalFrames.add(next);
        }
    }

    private void setFinalFrames(int number) {
        if(finalFrames.size() == 0 ){
            this.finalFrames.add(new FinalFrame(FINAL_FRAME_NO));
        }

        FinalFrame current = lastFinalFrame();
        FinalFrame next = current.next(number);
        if (current != next) {
            this.finalFrames.add(next);
        }
    }

    public int currentFrameNo() {
        if(finalFrames.size() > 0){
            return lastFinalFrame().getFrameNo();
        }
        return lastNormalFrame().getFrameNo();
    }

    private NormalFrame lastNormalFrame() {
        return this.normalFrames.get(this.normalFrames.size() - 1);
    }

    private FinalFrame lastFinalFrame() {
        return this.finalFrames.get(this.finalFrames.size() - 1);
    }

    public List<NormalFrame> getNormalFrames() {
        return this.normalFrames;
    }

    public List<FinalFrame> getFinalFrames() {
        return this.finalFrames;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Frames frames = (Frames) o;
        return Objects.equals(normalFrames, frames.normalFrames) &&
                Objects.equals(finalFrames, frames.finalFrames);
    }

    @Override
    public int hashCode() {
        return Objects.hash(normalFrames, finalFrames);
    }
}
