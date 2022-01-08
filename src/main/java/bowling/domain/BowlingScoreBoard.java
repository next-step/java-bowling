package bowling.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import bowling.domain.frame.BowlingFrame;
import bowling.engine.Bonus;
import bowling.engine.Frame;
import bowling.engine.Name;
import bowling.engine.Result;
import bowling.engine.ScoreBoard;
import bowling.engine.Sequence;
import bowling.engine.Shot;
import bowling.engine.Shots;
import bowling.engine.collection.FirstClassMutableList;

public class BowlingScoreBoard extends FirstClassMutableList<Frame> implements ScoreBoard {
    private final Name name;

    protected BowlingScoreBoard(Name name, List<Frame> frames) {
        super(frames);
        this.name = name;
    }

    public static BowlingScoreBoard of(String name) {
        List<Frame> frames = new ArrayList<>(NUMBER_OF_FRAME);
        frames.add(BowlingFrame.startFrame());
        return new BowlingScoreBoard(Player.of(name), frames);
    }

    public static BowlingScoreBoard of(String name, List<Frame> frames) {
        return new BowlingScoreBoard(Player.of(name), frames);
    }

    @Override
    public boolean empty(int sequence) {
        return elementOfOptional(sequence)
                .map(Frame::result)
                .map(Result::shots)
                .filter(Shots::notEmpty)
                .isEmpty();
    }

    @Override
    public boolean remainBonuses(int sequence) {
        return elementOfOptional(sequence)
                .map(Frame::result)
                .filter(Result::completed)
                .map(Result::bonus)
                .filter(Bonus::remain)
                .isPresent();
    }

    @Override
    public Frame nextShot(Shot shot) {
        Frame previous = last();
        if (previous.result().completed()) {
            return newFrame(shot);
        }

        Frame frame = previous.nextShot(shot);
        return setElement(indexOf(previous), frame);
    }

    private Frame newFrame(Shot shot) {
        Frame frame = last().nextShot(shot);
        append(frame);
        return frame;
    }

    @Override
    public Sequence current() {
        Frame last = last();
        if (last.isFinal() || !last().result().completed()) {
            return last.sequence();
        }

        return last.sequence()
                .next();
    }

    @Override
    public Name name() {
        return name;
    }

    @Override
    public boolean isEnded() {
        Frame last = last();
        return last.isFinal() && last.result().completed();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        BowlingScoreBoard that = (BowlingScoreBoard) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name);
    }
}
