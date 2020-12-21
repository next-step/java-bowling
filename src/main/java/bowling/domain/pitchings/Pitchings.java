package bowling.domain.pitchings;

import bowling.domain.KnockDownPins;
import bowling.domain.Pitching;
import bowling.domain.Score;
import bowling.dto.PitchingsDto;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Stream;

public abstract class Pitchings implements Iterable<Pitching> {
    final LinkedList<Pitching> value;
    Score score;

    Pitchings() {
        this.value = new LinkedList<>();
        score = Score.ofMiss(0);
    }

    public void addPitching(KnockDownPins knockDownPins) {
        Pitching pitching = getPitching(knockDownPins);
        value.add(pitching);
        score = renewScore(pitching);
    }

    abstract Score renewScore(Pitching pitching);

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
            return KnockDownPins.MAX_VALUE - firstPitching.getScore();
        }
        return pitching.getScore();
    }

    public Score getScore() {
        return score;
    }

    public abstract BiFunction<Integer, Score, Integer> calculateTotalScore();

    protected boolean canNotCalculateTotalScore(Integer previousFrameTotalScore, Score score) {
        return previousFrameTotalScore == null || score == null || !isEnd();
    }

    public PitchingsDto convertToDto() {
        List<Pitching> pitchings = Collections.unmodifiableList(value);
        return PitchingsDto.of(pitchings);
    }

    @Override
    public Iterator<Pitching> iterator() {
        return value.iterator();
    }

    @Override
    public String toString() {
        return "Pitchings{" +
                "value=" + value +
                ", score=" + score +
                ", isEnd()=" + isEnd() +
                '}';
    }
}
