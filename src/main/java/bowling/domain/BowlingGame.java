package bowling.domain;

import bowling.domain.dto.Record;
import bowling.domain.frame.FinalFrame;
import bowling.domain.frame.Frame;
import bowling.domain.frame.FrameType;
import bowling.domain.frame.NormalFrame;
import bowling.domain.scorestrategy.ScoreStrategy;
import bowling.domain.state.StateType;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BowlingGame {

    private static final int LAST_NORMAL_TURN = 9;

    private LinkedList<Frame> frames;

    public BowlingGame() {
        this.frames = new LinkedList<>();
        frames.add(new NormalFrame());
    }

    public Pin bowl(ScoreStrategy scoreStrategy) {
        Pin now = scoreStrategy.getScore(getRemainPins());
        addFrame();
        frames.getLast().bowl(now);
        return now;
    }

    private int getRemainPins() {
        return Optional.ofNullable(frames)
                .map(LinkedList::getLast)
                .filter(frame -> !frame.isFinish())
                .map(Frame::getRemainPins)
                .orElse(10);
    }

    private void addFrame() {

        if (normalFramesIsFinished()) {
            frames.add(new FinalFrame());
        }

        if (frames.getLast().isFinish()) {
            frames.add(new NormalFrame());
        }
    }


    public boolean isFinished() {
        return frames.size() == 10 && frames.getLast().isFinish();
    }

    private boolean normalFramesIsFinished() {
        return frames.size() == LAST_NORMAL_TURN && frames.getLast().isFinish();
    }

    public List<Record> getGameRecord() {
        return frames.stream()
                .map(this::getRecord)
                .collect(Collectors.toUnmodifiableList());
    }

    public int getFrameNumber() {
        return frames.size();
    }

    private Record getRecord(Frame frame) {

        return new Record(FrameType.valueOf(frame)
                , frame.getState().getRecord()
                , Optional.of(frame)
                .filter(FinalFrame.class::isInstance)
                .map(finalPin -> ((FinalFrame) finalPin).getBonus())
                .map(Pin::getValue)
                .orElse(null)
                , StateType.valueOf(frame.getState())
        );
    }
}
