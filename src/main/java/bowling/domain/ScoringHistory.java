package bowling.domain;

import bowling.constant.GameState;
import bowling.constant.ScoringStatus;
import bowling.ui.BowlingResultView;

public class ScoringHistory {
    private static final String INVALID_SECOND_SCORE_MESSAGE = "남은 핀의 개수 보다 많은 값입니다.";
    private static final ScoringHistory EMPTY_HISTORY = new ScoringHistory(BowlingScore.zeroScore()
            , BowlingScore.zeroScore(), ScoringStatus.EMPTY);

    private final BowlingScore firstRecord;
    private BowlingScore secondRecord = BowlingScore.zeroScore();
    private final ScoringStatus scoringStatus;

    public static ScoringHistory emptyHistory() {
        return EMPTY_HISTORY;
    }

    public static ScoringHistory firstTry(BowlingScore firstRecord) {
        if (firstRecord.isPerfect()) {
            return new ScoringHistory(firstRecord, ScoringStatus.STRIKE);
        }
        return new ScoringHistory(firstRecord, ScoringStatus.PLAYING);
    }

    private ScoringHistory(BowlingScore firstRecord, ScoringStatus scoringStatus) {
        this.firstRecord = firstRecord;
        this.scoringStatus = scoringStatus;
    }

    public static ScoringHistory secondTry(ScoringHistory scoringHistory, BowlingScore secondRecord) {
        validateInvalidSecondScore(scoringHistory.firstRecord, secondRecord);
        BowlingScore combinedScore = scoringHistory.firstRecord.add(secondRecord);
        if (combinedScore.isPerfect()) {
            return new ScoringHistory(scoringHistory.firstRecord, secondRecord, ScoringStatus.SPARE);
        }
        return new ScoringHistory(scoringHistory.firstRecord, secondRecord, ScoringStatus.DONE);
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
        if (isEmptyStatus()) {
            return "";
        }

        if (scoringStatus == ScoringStatus.STRIKE) {
            return ScoringStatus.STRIKE.getSymbol();
        }

        if (scoringStatus == ScoringStatus.SPARE) {
            return firstRecord.printableScore() +
                    BowlingResultView.SCORE_DELIMITER +
                    ScoringStatus.SPARE.getSymbol();
        }

        if (scoringStatus == ScoringStatus.PLAYING) {
            return firstRecord.printableScore();
        }

        return firstRecord.printableScore() +
                BowlingResultView.SCORE_DELIMITER +
                secondRecord.printableScore();
    }

    public boolean isEmptyStatus() {
        return scoringStatus == ScoringStatus.EMPTY;
    }

    public boolean isPlayingStatus() {
        return scoringStatus == ScoringStatus.PLAYING;
    }

    public GameState getGameState() {
        return scoringStatus.getState();
    }

    public boolean isSpareOrStrike() {
        return scoringStatus == ScoringStatus.SPARE || scoringStatus == ScoringStatus.STRIKE;
    }
}
