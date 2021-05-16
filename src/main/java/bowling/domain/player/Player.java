package bowling.domain.player;

import bowling.domain.HitNumber;
import bowling.domain.Score;
import bowling.domain.frame.Frame;
import bowling.domain.frame.NormalFrame;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Player {
    private static final int MAX_INDEX = 10;
    private final List<Frame> frames;
    private final Name name;

    private Player(List<Frame> frames, Name name) {
        this.frames = frames;
        this.name = name;
    }

    public static Player of(String name) {
        List<Frame> frameList = new ArrayList<>();
        frameList.add(NormalFrame.of());
        return new Player(frameList, Name.of(name));
    }

    public static Player of(List<Frame> frames, String name) {
        return new Player(frames, Name.of(name));
    }

    public boolean playWithNext(HitNumber hitNumber) {
        rollOne(hitNumber);
        addHitScore(hitNumber);
        if (isNeededToNext()) {
            addNext();
            return true;
        }
        return false;
    }

    public List<Score> totalScores() {
        List<Score> eachScore = getEachScore();
        for (int i = 1; i < eachScore.size(); i++) {
            eachScore.set(i, eachScore.get(i - 1).calculate(eachScore.get(i)));
        }
        return eachScore;
    }

    public Score totalScore() {
        return totalScores().get(MAX_INDEX - 1);
    }

    public boolean isFinished() {
        return frames.size() == MAX_INDEX && getLast().isFinished();
    }

    public int getLastIndex() {
        return frames.size();
    }

    public String toStringName() {
        return name.toString();
    }

    private void rollOne(HitNumber hitNumber) {
        setLast(getLast().roll(hitNumber));
    }

    private void addHitScore(HitNumber hitNumber) {
        for (int i = 0; i < frames.size() - 1; i++) {
            frames.set(i, hitNumber.addScore(frames.get(i)));
        }
    }

    private List<Score> getEachScore() {
        return frames.stream()
                .filter(frame -> frame.isFinished() && !frame.canAccumulate())
                .map(frame -> frame.totalScore())
                .collect(Collectors.toList());
    }

    private boolean isNeededToNext() {
        return frames.size() != MAX_INDEX && getLast().isFinished();
    }

    private void addNext() {
        frames.add(getLast().next(getLastIndex()));
    }

    private Frame getLast() {
        return frames.get(frames.size() - 1);
    }

    private void setLast(Frame frame) {
        frames.set(frames.size() - 1, frame);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(frames, player.frames) && Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(frames, name);
    }

    @Override
    public String toString() {
        return "" + name + ":" + frames + "";
    }
}
