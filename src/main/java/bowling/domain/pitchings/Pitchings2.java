package bowling.domain.pitchings;

import bowling.domain.KnockDownPins;
import bowling.domain.Pitching;
import bowling.domain.Score;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public abstract class Pitchings2 implements Iterable<Pitching> {
    final List<Pitching> value;
    public Score score;

    protected Pitchings2() {
        this.value = new ArrayList<>();
        score = Score.ofMiss(0);
    }

    public void addPitching(KnockDownPins knockDownPins) {
        Pitching pitching = getPitching(knockDownPins);
        value.add(pitching);
        score = renewScore();
    }

    private Score renewScore() {
        if (isStrike()) {
            return Score.ofStrike();
        }

        if (isSpare()) {
            return Score.ofSpare();
        }

        return Score.ofMiss(calculatePitchingScore());
    }

    private int calculatePitchingScore() {
        return value.stream()
                .mapToInt(Pitching::getScore)
                .sum();
    }

    private Pitching getPitching(KnockDownPins knockDownPins) {
        if (value.isEmpty()) {
            return Pitching.getPitching(knockDownPins);
        }

        int lastIndex = value.size() - 1;
        Pitching previousPitching = value.get(lastIndex);
        return Pitching.getPitching(knockDownPins, previousPitching);
    }

    public boolean leftBonusApplyChance() {
        return score != null && score.leftBonusApplyChance();
    }

    boolean isStrike() {
        return value.contains(Pitching.STRIKE);
    }

    boolean isSpare() {
        return value.contains(Pitching.SPARE);
    }

    public abstract boolean isEnd();

    @Override
    public Iterator<Pitching> iterator() {
        return value.iterator();
    }

    public void addBonusScoreTo(Pitchings2 previousPitchings) {
        if (value.isEmpty()) {
            return;
        }

        for (Pitching pitching : value) {
            if (previousPitchings.leftBonusApplyChance()) {
                previousPitchings.score.addBonusScore(pitching.getScore());
            }
        }
    }

    public Score getScore() {
        return score;
    }

    public Stream<Pitching> stream() {
        return value.stream();
    }

    @Override
    public String toString() {
        return "Pitchings2{" +
                "value=" + value +
                ", score=" + score +
                ", isEnd()=" + isEnd() +
                '}';
    }
}
