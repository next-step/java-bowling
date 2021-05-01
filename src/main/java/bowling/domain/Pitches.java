package bowling.domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

public class Pitches implements Iterable<Pitch> {

    private static final int DEFAULT_PITCH_ABLE_COUNT = 2;
    private static final int FIRST_INDEX = 0;
    private static final String SPARE_MARK = "/";

    private final List<Pitch> values;
    private int pitchAbleCount;

    public Pitches() {
        this(new ArrayList<>());
    }

    public Pitches(List<Pitch> values) {
        this.values = values;
        this.pitchAbleCount = DEFAULT_PITCH_ABLE_COUNT;
    }

    public int pinDownCount() {
        return values.stream()
                .mapToInt(Pitch::value)
                .sum();
    }

    public int firstPinDownCount() {
        return first().value();
    }

    public int count() {
        return values.size();
    }

    public void add(Pitch pitch) {
        validatePitch(pitch);
        values.add(pitch);
    }

    private void validatePitch(Pitch pitch) {
        if (pitch == null) {
            throw new IllegalArgumentException("투구 정보를 입력해 주세요.");
        }
        if (isFinished()) {
            throw new IllegalStateException("종료된 프레임입니다.");
        }
        if (!isFinished() && spare() < pitch.value()) {
            throw new IllegalArgumentException("핀 처리횟수가 남은 핀수를 초과합니다. 투구정보를 확인해 주세요.");
        }
    }

    public boolean isStrike() {
        return !isEmpty() && first().isStrike();
    }

    public boolean isSpare() {
        return pinDownCount() == Pitch.STRIKE_COUNT && count() == DEFAULT_PITCH_ABLE_COUNT;
    }

    public boolean isMiss() {
        return isFinished() && pinDownCount() < Pitch.STRIKE_COUNT;
    }

    private Pitch first() {
        return values.get(FIRST_INDEX);
    }

    public int spare() {
        if (isStrike() || isSpare()) {
            return Pitch.STRIKE_COUNT;
        }
        return Pitch.STRIKE_COUNT - pinDownCount();
    }

    public boolean isFinished() {
        return count() == this.pitchAbleCount;
    }

    public boolean isEmpty() {
        return values.isEmpty();
    }

    public void increasePitchAbleCount() {
        this.pitchAbleCount++;
    }

    public void decreasePitchAbleCount() {
        this.pitchAbleCount--;
    }

    @Override
    public Iterator<Pitch> iterator() {
        return values.iterator();
    }

    @Override
    public void forEach(Consumer<? super Pitch> action) {
        Iterable.super.forEach(action);
    }

    @Override
    public Spliterator<Pitch> spliterator() {
        return Iterable.super.spliterator();
    }
}
