package bowling.domain;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import bowling.engine.BonusScores;
import bowling.engine.Frame;
import bowling.engine.Score;
import bowling.engine.Sequence;
import bowling.engine.Shot;
import bowling.engine.Shots;

public class NormalFrame implements Frame {
    public static final Frame START_FRAME = new NormalFrame(FrameSequence.FIRST_FRAME, FrameShots.EMPTY_SHOT, Collections.emptyList());

    private final Sequence sequence;
    // todo extract FrameResult
    protected final Shots shots;

    // todo 리스트를 썼지만 사실 최대 3개다. 전 프레임까지 더블을 쳤을때 2개, 아니면 전 프레임에서 클리어 했을 경우엔 하나다. 그리고 하나는 내 보너스
    // todo 점수 계산을 위해 내 보너스와 전프레임 보너스는 구분되어야 한다.
    // todo 보너스 일급 클래스로 해결 가능할듯?
    private final List<BonusScores> bonusScoresList;

    protected NormalFrame(Sequence sequence, Shots shots, List<BonusScores> bonusScoresList) {
        this.sequence = sequence;
        this.shots = shots;
        this.bonusScoresList = bonusScoresList;
    }

    // todo 여기 파라미터는 전 보너스
    static Frame of(Sequence sequence, Shots shots, List<BonusScores> bonusScoresList) {
        if (sequence == null || shots == null || bonusScoresList == null) {
            throw new IllegalArgumentException("sequence or shots cannot be null");
        }

        if (sequence.isFinal()) {
            return FinalFrame.of(shots);
        }

        // todo 이건 내 보너스
        Optional<BonusScores> newBonus = newBonus(shots);
        return new NormalFrame(sequence, shots, Stream.concat(newBonus.stream(), bonusScoresList.stream()).collect(Collectors.toList()));
    }

    // todo move to Shots and test.
    private static Optional<BonusScores> newBonus(Shots shots) {
        return shots.lastOptional()
                .filter(shot -> shots.isClear())
                .map(shot -> shot.isSpare() ? ClearBonusScores.bySpare() : ClearBonusScores.byStrike());
    }

    static Frame of(Sequence sequence, List<Shot> shots, List<BonusScores> bonusScoresList) {
        if (shots == null) {
            throw new IllegalArgumentException("sequence or shots cannot be null");
        }

        return of(sequence, FrameShots.of(shots), bonusScoresList);
    }

    public static Frame first(Sequence sequence, Shot shot) {
        if (shot == null) {
            throw new IllegalArgumentException("the first shot cannot be null");
        }
        return of(sequence, List.of(shot), Collections.emptyList());
    }

    @Override
    public Frame nextShot(Shot shot) {
        if (shot == null) {
            throw new IllegalArgumentException("the shot cannot be null");
        }

        applyBonus(shot);
        List<BonusScores> remains = remainBonus();


        if (completed()) {
            return NormalFrame.of(sequence.next(), List.of(shot), remains);
        }

        return NormalFrame.of(sequence, shots.nextShot(shot), remains);
    }

    // todo test
    public void applyBonus(Shot shot) {
        bonusScoresList.stream()
                .filter(bonus -> !bonus.completed())
                .forEach(bonus -> bonus.appendBonus(FrameScore.of(shot.toInt())));
    }

    // todo test
    public List<BonusScores> remainBonus() {
        return bonusScoresList.stream()
                .filter(bonus -> !bonus.completed())
                .collect(Collectors.toList());
    }

    @Override
    public Sequence sequence() {
        return sequence;
    }

    @Override
    public Score score() {
        return shots.score();
    }

    @Override
    public boolean hasThirdChance() {
        return false;
    }

    @Override
    public boolean complectedBonus() {
        return bonusScoresList.stream()
                .allMatch(BonusScores::completed);
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
