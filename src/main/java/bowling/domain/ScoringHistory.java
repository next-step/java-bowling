package bowling.domain;

import bowling.constant.ScoringStatus;

public class ScoringHistory {
    private static final String INVALID_SECOND_SCORE_MESSAGE = "남은 핀의 갯수보다 많은 값입니다.";
    private static final String SCORE_DELIMITER = " | ";
    private BowlingScore firstRecord;
    private BowlingScore secondRecord;
    private ScoringStatus scoringStatus;

    public static ScoringHistory from(BowlingScore firstRecord) {
        if (firstRecord.isPerfect()) {
            return new ScoringHistory(firstRecord, ScoringStatus.STRIKE);
        }
        return new ScoringHistory(firstRecord, ScoringStatus.NONE);
    }

    private ScoringHistory(BowlingScore firstRecord, ScoringStatus scoringStatus) {
        this.firstRecord = firstRecord;
        this.scoringStatus = scoringStatus;
    }

    public static ScoringHistory of(ScoringHistory scoringHistory, BowlingScore secondRecord) {
        validateInvalidSecondScore(scoringHistory.firstRecord, secondRecord);
        BowlingScore combinedScore = scoringHistory.firstRecord.add(secondRecord);
        if (combinedScore.isPerfect()) {
            return new ScoringHistory(scoringHistory.firstRecord, secondRecord, ScoringStatus.SPARE);
        }
        return new ScoringHistory(scoringHistory.firstRecord, secondRecord, ScoringStatus.NONE);
    }

    private static void validateInvalidSecondScore(BowlingScore firstRecord, BowlingScore secondRecord) {
        if (BowlingScore.isExceedLimitToAdd(firstRecord, secondRecord)) {
            throw new IllegalArgumentException(INVALID_SECOND_SCORE_MESSAGE);
        }
    }

    private ScoringHistory(BowlingScore firstRecord, BowlingScore secondRecord, ScoringStatus scoringStatus) {
        this.firstRecord = firstRecord;
        this.secondRecord = secondRecord;
        this.scoringStatus = scoringStatus;
    }

    public String printableScoringHistoryStatus() {
        if (scoringStatus == ScoringStatus.STRIKE) {
            return ScoringStatus.STRIKE.getSymbol();
        }

        if (scoringStatus == ScoringStatus.SPARE) {
            return firstRecord.printableScore() +
                    SCORE_DELIMITER +
                    ScoringStatus.SPARE.getSymbol();
        }

        return firstRecord.printableScore() +
                SCORE_DELIMITER +
                secondRecord.printableScore();
    }
}
