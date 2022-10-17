package bowling.domain;

import bowling.domain.dto.Record;
import bowling.domain.frame.FinalFrame;
import bowling.domain.frame.Frame;
import bowling.domain.frame.FrameType;
import bowling.domain.frame.NormalFrame;
import bowling.domain.scorestrategy.ScoreStrategy;
import bowling.domain.state.StateType;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BowlingGame {

    private static final int NORMAL_TURN = 9;

    private LinkedList<Frame> frames;
    private final PlayerName playerName;
    private Pin now;

    public BowlingGame(PlayerName playerName) {
        this.playerName = playerName;
        this.frames = new LinkedList<>();
        frames.add(new NormalFrame());
    }

    public void bowl(ScoreStrategy scoreStrategy) {

        now = scoreStrategy.getScore(getRemainPins());

        if (isFinished()) {
            throw new IllegalStateException("게임이 종료되었습니다.");
        }

        addFrame();
        frames.getLast().bowl(now);
    }

    private int getRemainPins() {
        return Optional.ofNullable(frames)
                .map(LinkedList::getLast)
                .filter(frame -> !frame.isFinish())
                .map(Frame::getRemainPins)
                .orElse(10);
    }

    private void addFrame() {

        if(normalFramesIsFinished()){
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
        return frames.size() == NORMAL_TURN && frames.getLast().isFinish();
    }

    public List<Record> getGameRecord() {
        List<Record> records = IntStream.range(0, 10)
                .mapToObj(i -> new Record())
                .collect(Collectors.toList());

        IntStream.range(0, frames.size())
                .forEach(i -> records.set(i, getRecord(frames.get(i))));

        return Collections.unmodifiableList(records);
    }

    public int getFrameNumber() {
        return frames.size();
    }

    public Pin getNow() {
        return now;
    }

    public PlayerName getPlayerName() {
        return playerName;
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
