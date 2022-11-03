package bowling.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import static java.util.stream.Collectors.toMap;

public class Bowling {

    private final Map<Position, BowlingRound> rounds = new HashMap<>();

    private final Positions positions;

    public Bowling() {
        this(1);
    }

    public Bowling(int position) {
        Position startPosition = new Position(position);
        this.rounds.put(startPosition, new BowlingRound(position));
        this.positions = new Positions(new Position(position));
    }

    public Bowling(int position, int calculatedPosition) {
        Position startPosition = new Position(position);
        Position calculatePosition = new Position(calculatedPosition);
        this.rounds.put(startPosition, new BowlingRound(position));
        this.positions = new Positions(startPosition, calculatePosition);
    }

    public void play(int numberOfPins, ScoreResult scoreResult) {
        BowlingRound round = currentRound();
        round.addKnockDownPins(numberOfPins);
        if (round.isNextRound()) {
            Position nextPosition = positions.next();
            BowlingRound nextRound = round.next();
            rounds.put(nextPosition, nextRound);
        }
        calculateScore(scoreResult);
    }

    private void calculateScore(ScoreResult scoreResult) {
        Position calculatePosition = positions.currentCalculatePosition();
        BowlingRound unCalculatedRound = rounds.get(calculatePosition);
        if (unCalculatedRound.isSelfCalculable()) {
            positions.increaseCalculatePosition();
            scoreResult.addScore(unCalculatedRound.sumScores());
        }
        Position position = calculatePosition.next();
        BowlingRound nextRound = rounds.get(position);
        if (nextRound == null) {
            return;
        }
        if (unCalculatedRound.containsSpare() && nextRound.hasScore()) {
            addSpareBonus(scoreResult, nextRound);
        }
        if (unCalculatedRound.containsStrike() && nextRound.hasTwoScore()) {
            addSingleStrikeBonus(scoreResult, nextRound);
        }
        if (unCalculatedRound.containsStrike() && nextRound.isFirstScoreStrike()) {
            addDoubleStrikeBonus(scoreResult, position);
        }

    }

    private void addSpareBonus(ScoreResult scoreResult, BowlingRound nextRound) {
        positions.increaseCalculatePosition();
        scoreResult.addScore(nextRound.spareBonus());
    }

    private void addSingleStrikeBonus(ScoreResult scoreResult, BowlingRound nextRound) {
        positions.increaseCalculatePosition();
        scoreResult.addScore(nextRound.sumScores() + 10);
        calculateScore(scoreResult);
    }

    private void addDoubleStrikeBonus(ScoreResult scoreResult, Position position) {
        BowlingRound nextNextRound = rounds.get(position.next());
        if (nextNextRound != null && nextNextRound.hasScore()) {
            positions.increaseCalculatePosition();
            scoreResult.addScore(nextNextRound.doubleStrikeBonus());
        }
    }

    public boolean isFinish() {
        return currentRound().isFinish();
    }


    public BowlingRound currentRound() {
        return rounds.get(positions.currentPosition());
    }

    public Map<Position, BowlingRound> getRounds() {
        return rounds.entrySet()
                .stream()
                .collect(toMap(Entry::getKey, Entry::getValue));
    }

}
