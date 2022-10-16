package bowling.domain;

import bowling.domain.dto.Record;
import bowling.domain.frame.FinalFrame;
import bowling.domain.frame.Frame;
import bowling.domain.frame.NormalFrame;
import bowling.domain.scorestrategy.ScoreStrategy;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BowlingGame {

    private static final int NORMAL_TURN = 9;

    private LinkedList<NormalFrame> normalFrames;
    private FinalFrame finalFrame;
    private final PlayerName playerName;
    private Score now;

    public BowlingGame(PlayerName playerName) {
        this.playerName = playerName;
        this.normalFrames = new LinkedList<>();
        setNormalFrame();
    }

    public void bowl(ScoreStrategy scoreStrategy) {

        now = scoreStrategy.getScore(getRemainPins());

        if (isFinished()) {
            throw new IllegalStateException("게임이 종료되었습니다.");
        }

        if (normalFramesIsFinished()) {
            setFinalFrame();
            finalFrame.bowl(now);
            return;
        }

        setNormalFrame();
        normalFrames.getLast().bowl(now);
    }

    private int getRemainPins() {
        if (normalFramesIsFinished()) {
            return Optional.ofNullable(finalFrame)
                    .map(Frame::getRemainPins)
                    .orElse(10);
        }
        return Optional.ofNullable(normalFrames)
                .map(LinkedList::getLast)
                .filter(frame -> !frame.isFinish())
                .map(Frame::getRemainPins)
                .orElse(10);
    }

    private void setNormalFrame() {
        if (normalFrames.isEmpty() || normalFrames.getLast().isFinish()) {
            normalFrames.add(new NormalFrame());
        }
    }

    private void setFinalFrame() {

        if (finalFrame == null) {
            finalFrame = new FinalFrame();
        }
    }


    public boolean isFinished() {
        return normalFramesIsFinished() && Optional.ofNullable(finalFrame)
                .map(FinalFrame::isFinish).orElse(false);
    }

    private boolean normalFramesIsFinished() {
        return normalFrames.size() == NORMAL_TURN && normalFrames.getLast().isFinish();
    }

    public List<Record> getGameRecord() {
        List<Record> records = IntStream.range(0, 10)
                .mapToObj(i -> new Record())
                .collect(Collectors.toList());

        IntStream.range(0, normalFrames.size())
                .forEach(i -> records.set(i, normalFrames.get(i).getRecord()));
        records.set(9, Optional.ofNullable(finalFrame)
                .map(FinalFrame::getRecord)
                .orElse(null));

        return Collections.unmodifiableList(records);
    }

    public int getFrameNumber() {
        return normalFrames.size()+1;
    }

    public Score getNow() {
        return now;
    }

    public PlayerName getPlayerName() {
        return playerName;
    }

}
