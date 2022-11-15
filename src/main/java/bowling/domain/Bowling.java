package bowling.domain;

import java.util.Map;

public class Bowling {

    private final BowlingRounds rounds = new BowlingRounds();

    private Position calculatePosition;

    public Bowling() {
        this.calculatePosition = new Position(1);
    }


    public ScoreResult play(int numberOfPins, Username username) {
        rounds.addKnockDownPins(numberOfPins);
        return calculateScore(ScoreResult.ofUsername(username));
    }

    private ScoreResult calculateScore(ScoreResult scoreResult) {
        rounds.findRoundByPosition(calculatePosition)
                .ifPresent((round) -> {
                    calculateWithOutBonus(scoreResult, round);
                    Position nextPosition = calculatePosition.next();
                    calculateWithBonus(scoreResult, nextPosition, round);
                });
        return scoreResult;

    }

    private void calculateWithOutBonus(ScoreResult scoreResult, BowlingRound round) {
        if (!round.isSelfCalculable()) {
            return;
        }
        increaseCalculatePosition();
        scoreResult.addScore(round.sumScores());
    }


    private void calculateWithBonus(ScoreResult scoreResult, Position nextPosition, BowlingRound previousRound) {
        rounds.findRoundByPosition(nextPosition)
                .ifPresent((round) -> calculateWithBonus(scoreResult, round, previousRound, nextPosition));
    }

    private void calculateWithBonus(ScoreResult scoreResult, BowlingRound round, BowlingRound previousRound, Position position) {
        if (previousRound.containsSpare() && round.hasScore()) {
            addSpareBonus(scoreResult, round);
        }
        if (previousRound.containsStrike() && round.hasTwoScore()) {
            addSingleStrikeBonus(scoreResult, round);
        }
        if (previousRound.containsStrike() && round.isFirstScoreStrike()) {
            addDoubleStrikeBonus(scoreResult, position);
        }
    }

    private void addSpareBonus(ScoreResult scoreResult, BowlingRound nextRound) {
        increaseCalculatePosition();
        scoreResult.addScore(nextRound.spareBonus());
    }

    private void addSingleStrikeBonus(ScoreResult scoreResult, BowlingRound nextRound) {
        increaseCalculatePosition();
        scoreResult.addScore(nextRound.sumScores() + 10);
        calculateScore(scoreResult);
    }

    private void increaseCalculatePosition() {
        calculatePosition = calculatePosition.next();
    }

    private void addDoubleStrikeBonus(ScoreResult scoreResult, Position position) {
        Position nextPosition = position.next();
        rounds.findRoundByPosition(nextPosition)
                .ifPresent((round) -> addDoubleStrikeBonus(scoreResult, round));
    }

    private void addDoubleStrikeBonus(ScoreResult scoreResult, BowlingRound round) {
        if (!round.hasScore()) {
            return;
        }
        increaseCalculatePosition();
        scoreResult.addScore(round.doubleStrikeBonus());
    }


    public boolean isFinish() {
        return rounds.isFinish();
    }

    public BowlingRound currentRound() {
        return rounds.currentRound();
    }

    public Map<Position, BowlingRound> getRounds() {
        return rounds.getRounds();
    }
}
