package bowling.domain.frame;

import bowling.domain.Pin;
import bowling.domain.scorestrategy.ScoreStrategy;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class Frames {

    private static final int LAST_NORMAL_TURN = 9;
    private static final int TOTAL_TURN = 10;

    private LinkedList<Frame> frames;

    public Frames() {
        this.frames = new LinkedList<>();
        frames.add(new NormalFrame());
    }

    public Pin bowl(ScoreStrategy scoreStrategy) {
        Pin now = scoreStrategy.getScore(getRemainPins());
        addFrame();
        calculatePoint(now);
        frames.getLast().bowl(now);
        return now;
    }

    private void calculatePoint(Pin now) {
        frames.stream()
                .filter(Frame::isFinish)
                .filter(Frame::canAddPoint)
                .forEach(frame -> frame.addPoint(now));
    }

    private int getRemainPins() {
        return Optional.ofNullable(frames)
                .map(LinkedList::getLast)
                .filter(frame -> !frame.isFinish())
                .map(Frame::getRemainPins)
                .orElse(10);
    }

    private void addFrame() {

        if(frames.size() == TOTAL_TURN && frames.getLast().isFinish()){
            throw new UnsupportedOperationException("10 프레임 이상 경기를 진행할 수 없습니다.");
        }

        if (normalFramesIsFinished()) {
            frames.add(new FinalFrame(frames.getLast()));
        }

        if (frames.getLast().isFinish()) {
            frames.add(new NormalFrame(frames.getLast()));
        }
    }


    public boolean isFinished() {
        return frames.size() == 10 && frames.getLast().isFinish();
    }

    private boolean normalFramesIsFinished() {
        return frames.size() == LAST_NORMAL_TURN && frames.getLast().isFinish();
    }

    public List<Frame> getFrames() {
        return Collections.unmodifiableList(frames);
    }

    public int getFrameNumber() {
        return frames.size();
    }

}
