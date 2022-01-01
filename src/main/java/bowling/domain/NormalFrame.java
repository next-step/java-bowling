package bowling.domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import bowling.engine.Bonus;
import bowling.engine.BonusScores;
import bowling.engine.Frame;
import bowling.engine.Score;
import bowling.engine.Sequence;
import bowling.engine.Shot;
import bowling.engine.Shots;

public class NormalFrame implements Frame {
    private final Sequence sequence;
    // todo extract FrameResult
    protected final Shots shots;
    protected final Bonus bonus;

    protected NormalFrame(Sequence sequence, Shots shots, Bonus bonus) {
        this.sequence = sequence;
        this.shots = shots;
        this.bonus = bonus;
    }

    static Frame of(Sequence sequence, Shots shots, Bonus bonus) {
        if (sequence == null || shots == null || bonus == null) {
            throw new IllegalArgumentException("sequence or shots or bonus cannot be null");
        }

        if (sequence.isFinal()) {
            return FinalFrame.of(shots, bonus);
        }

        return new NormalFrame(sequence, shots, bonus);
    }

    static Frame of(Sequence sequence, Shots shots, List<BonusScores> bonusScoresList) {
        if (shots == null) {
            throw new IllegalArgumentException("shots cannot be null");
        }

        return of(sequence, shots, FrameBonus.of(bonusScoresList, shots.clearBonus()));
    }

    static Frame of(Sequence sequence, List<Shot> shots, List<BonusScores> bonusScoresList) {
        return of(sequence, FrameShots.of(shots), bonusScoresList);
    }

    public static Frame ready(Sequence sequence, Shot shot) {
        if (shot == null) {
            throw new IllegalArgumentException("the first shot cannot be null");
        }

        return of(sequence, List.of(shot), Collections.emptyList());
    }

    public static Frame startFrame() {
        return new NormalFrame(FrameSequence.FIRST_FRAME, FrameShots.emptyShot(), FrameBonus.NONE);
    }

    @Override
    public Frame nextShot(Shot shot) {
        if (shot == null) {
            throw new IllegalArgumentException("the shot cannot be null");
        }

        bonus.applyBonus(shot);

        if (completed()) {
            return NormalFrame.of(sequence.next(), List.of(shot), bonus.remainBonus());
        }

        return NormalFrame.of(sequence, shots.nextShot(shot), bonus.remainBonus());
    }

    @Override
    public Sequence sequence() {
        return sequence;
    }

    @Override
    public Score score() {
        // todo refactor Score#add
        return FrameScore.of(shots.score().toInt() + bonus.score().toInt());
    }

    @Override
    public boolean hasThirdChance() {
        return false;
    }

    @Override
    public boolean complectedBonus() {
        return bonus.completed();
    }

    @Override
    public boolean completed() {
        return shots.size() == Shots.NUMBER_OF_SHOT || shots.isClear();
    }

    @Override
    public boolean isFinal() {
        return false;
    }

    @Override
    public Stream<Shot> stream() {
        return shots.stream();
    }

    @Override
    public String toString() {
        return "NormalFrame{" +
                "sequence=" + sequence +
                ", shots=" + shots +
                '}';
    }
}
