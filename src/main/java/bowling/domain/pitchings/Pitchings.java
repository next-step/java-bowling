package bowling.domain.pitchings;

import bowling.domain.KnockDownPins;
import bowling.domain.Pitching;
import bowling.domain.Score;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.stream.Stream;

public abstract class Pitchings implements Iterable<Pitching> {
    final LinkedList<Pitching> value;
    private Score score;

    protected Pitchings() {
        this.value = new LinkedList<>();
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

        Pitching previousPitching = value.getLast();
        return Pitching.getPitching(knockDownPins, previousPitching);
    }

    boolean isStrike() {
        return value.contains(Pitching.STRIKE);
    }

    boolean isSpare() {
        return value.contains(Pitching.SPARE);
    }

    public abstract boolean isEnd();

    public Score applyBonusScoreTo(Score previousScore) {
        Iterator<Pitching> iterator = value.iterator();
        while (iterator.hasNext() && previousScore.leftBonusApplyChance()) {
            Pitching pitching = iterator.next();
            int bonusScore = getBonusScore(pitching);
            previousScore = previousScore.addBonusScore(bonusScore);
        }
        return previousScore;
    }

    private int getBonusScore(Pitching pitching) {
        if (pitching == Pitching.SPARE) {
            Pitching firstPitching = value.get(0);
            return 10 - firstPitching.getScore();
        }
        return pitching.getScore();
    }

    public Score getScore() {
        return score;
    }

    public Stream<Pitching> stream() {
        return value.stream();
    }

    @Override
    public Iterator<Pitching> iterator() {
        return value.iterator();
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
