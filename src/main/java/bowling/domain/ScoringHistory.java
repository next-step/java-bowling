package bowling.domain;

import bowling.constant.ScoringStatus;

public class ScoringHistory {
    private static final String INVALID_SECOND_SCORE_MESSAGE = "남은 핀의 개수 보다 많은 값입니다.";
    private static final ScoringHistory EMPTY_HISTORY = new EmptyScoringHistory();

    private BowlingScore firstRecord = BowlingScore.emptyScore();
    private BowlingScore secondRecord = BowlingScore.emptyScore();
    private ScoringStatus scoringStatus;

    private ScoringHistory() {

    }

    public static ScoringHistory emptyHistory() {
        return EMPTY_HISTORY;
    }

    public static ScoringHistory firstTry(BowlingScore firstRecord) {
        return new ScoringHistory(firstRecord);
    }

    private ScoringHistory(BowlingScore firstRecord) {
        this.firstRecord = firstRecord;
        this.scoringStatus = ScoringStatus.findScoringStatus(firstRecord, BowlingScore.emptyScore());
    }

    public static ScoringHistory secondTry(ScoringHistory scoringHistory, BowlingScore secondRecord) {
        validateInvalidSecondScore(scoringHistory.firstRecord, secondRecord);
        return new ScoringHistory(scoringHistory.firstRecord, secondRecord);
    }

    private static void validateInvalidSecondScore(BowlingScore firstRecord, BowlingScore secondRecord) {
        if (BowlingScore.isExceedLimitToAdd(firstRecord, secondRecord)) {
            throw new IllegalArgumentException(INVALID_SECOND_SCORE_MESSAGE);
        }
    }

    private ScoringHistory(BowlingScore firstRecord, BowlingScore secondRecord) {
        this.firstRecord = firstRecord;
        this.secondRecord = secondRecord;
        this.scoringStatus = ScoringStatus.findScoringStatus(firstRecord, secondRecord);
    }

    public String printableScoringHistoryStatus() {
        return scoringStatus.printScore(firstRecord, secondRecord);
    }

    public boolean isDone() {
        return scoringStatus == ScoringStatus.STRIKE || !secondRecord.isEmpty();
    }

    public boolean isEmpty() {
        return this.equals(EMPTY_HISTORY);
    }

    private static class EmptyScoringHistory extends ScoringHistory{
        @Override
        public String printableScoringHistoryStatus() {
            return "";
        }

        @Override
        public boolean isDone() {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean isEmpty() {
            return true;
        }
    }
}
