package bowling.domain;

import bowling.constant.ScoringStatus;

public class BonusScoringHistory {
    private static final BonusScoringHistory EMPTY_BONUS_HISTORY = new EmptyBonusScoringHistory();

    private BowlingScore firstBonusRecord = BowlingScore.emptyScore();
    private BowlingScore secondBonusRecord = BowlingScore.emptyScore();
    private BowlingScore thirdBonusRecord = BowlingScore.emptyScore();

    private BonusScoringHistory() {

    }

    public static BonusScoringHistory emptyBonusHistory() {
        return EMPTY_BONUS_HISTORY;
    }

    public static BonusScoringHistory firstTry(BowlingScore bowlingScore) {
        return new BonusHistoryBuilder(bowlingScore)
                .build();
    }

    public static BonusScoringHistory secondTry(BonusScoringHistory bonusScoringHistory, BowlingScore bowlingScore) {
        return new BonusHistoryBuilder(bonusScoringHistory.firstBonusRecord)
                .setSecondBonusRecord(bowlingScore)
                .build();
    }

    public static BonusScoringHistory thirdTry(BonusScoringHistory bonusScoringHistory, BowlingScore bowlingScore) {
        return new BonusHistoryBuilder(bonusScoringHistory.firstBonusRecord)
                .setSecondBonusRecord(bonusScoringHistory.secondBonusRecord)
                .setThirdBonusRecord(bowlingScore)
                .build();
    }

    public String printableBonusHistoryStatus() {
        if (!firstBonusRecord.isPerfect()) {
            return printableBonusRecord(firstBonusRecord, secondBonusRecord) + "|" + printableBonusRecord(thirdBonusRecord, BowlingScore.emptyScore());
        }

        if (!secondBonusRecord.isPerfect()) {
            return printableBonusRecord(firstBonusRecord, BowlingScore.emptyScore()) + "|" + printableBonusRecord(secondBonusRecord, thirdBonusRecord);
        }

        return printableBonusRecord(firstBonusRecord, BowlingScore.emptyScore()) + "|"
                + printableBonusRecord(secondBonusRecord, BowlingScore.emptyScore()) + "|"
                + printableBonusRecord(thirdBonusRecord, BowlingScore.emptyScore());
    }

    private String printableBonusRecord(BowlingScore firstBonusRecord, BowlingScore secondBonusRecord) {
        return ScoringStatus.findScoringStatus(firstBonusRecord, secondBonusRecord).printScore(firstBonusRecord, secondBonusRecord);
    }

    public boolean isFirstTry() {
        return this.equals(EMPTY_BONUS_HISTORY);
    }

    public boolean isSecondTry() {
        return secondBonusRecord.isEmpty();
    }

    public boolean isDone() {
        return !thirdBonusRecord.isEmpty() ||
                (!firstBonusRecord.isPerfect()
                        && !secondBonusRecord.isEmpty()
                        && !firstBonusRecord.add(secondBonusRecord).isPerfect());
    }

    private static class BonusHistoryBuilder {
        private BowlingScore firstBonusRecord;
        private BowlingScore secondBonusRecord = BowlingScore.emptyScore();
        private BowlingScore thirdBonusRecord = BowlingScore.emptyScore();

        private BonusHistoryBuilder(BowlingScore firstBonusRecord) {
            this.firstBonusRecord = firstBonusRecord;
        }

        private BonusHistoryBuilder setSecondBonusRecord(BowlingScore secondBonusRecord) {
            this.secondBonusRecord = secondBonusRecord;
            return this;
        }

        private BonusHistoryBuilder setThirdBonusRecord(BowlingScore thirdBonusRecord) {
            this.thirdBonusRecord = thirdBonusRecord;
            return this;
        }

        private BonusScoringHistory build() {
            BonusScoringHistory bonusScoringHistory = new BonusScoringHistory();
            bonusScoringHistory.firstBonusRecord = firstBonusRecord;
            bonusScoringHistory.secondBonusRecord = secondBonusRecord;
            bonusScoringHistory.thirdBonusRecord = thirdBonusRecord;
            return bonusScoringHistory;
        }
    }

    private static class EmptyBonusScoringHistory extends BonusScoringHistory {
        @Override
        public String printableBonusHistoryStatus() {
            return "";
        }
    }
}
