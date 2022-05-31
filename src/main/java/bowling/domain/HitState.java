//package bowling.domain;
//
//import java.util.OptionalInt;
//
//public enum HitState {
////    NORMAL(frame -> OptionalInt.of(frame.firstScore() + frame.secondScore())),
////    SPARE(frame -> {
////        if (!frame.hasNext()) {
////            return OptionalInt.empty();
////        }
////
////        Frame next = frame.next();
////
////        if (next.isNotThrowFirstYet()) {
////            return OptionalInt.empty();
////        }
////        return OptionalInt.of(next.firstScore() + 10);
////    }),
////    STRIKE(frame -> {
////        Frame currentFrame = frame.next();
////        if (currentFrame == null || currentFrame.isNotThrowFirstYet()) {
////            return OptionalInt.empty();
////        }
////
////        TotalScore totalScore = TotalScore.ofInitialStrike();
////        BonusShotCount bonusCount = BonusShotCount.ofStrike();
////        while (currentFrame != null && bonusCount.isRemained()) {
////            totalScore.appendScoreOf(bonusCount, currentFrame);
////            currentFrame = currentFrame.next();
////        }
////
////        return resultScoreAsOptional(totalScore, bonusCount);
////    });
//
////    private static OptionalInt resultScoreAsOptional(TotalScore totalScore, BonusShotCount count) {
////        if (count.isRemained()) {
////            return OptionalInt.empty();
////        }
////        return totalScore.getAsOptional();
////    }
//
//    private final ScoreCalculator calculator;
//
////    public OptionalInt scoreOf(Frame frame) {
////        return calculator.calculate(frame);
////    }
//
//    HitState(ScoreCalculator calculator) {
//        this.calculator = calculator;
//    }
//
////    protected static class BonusShotCount {
////
////        private static final int STRIKE_BONUS_COUNT = 2;
////
////        private int count;
////
////        private BonusShotCount(int count) {
////            this.count = count;
////        }
////
////        public static BonusShotCount ofStrike() {
////            return new BonusShotCount(STRIKE_BONUS_COUNT);
////        }
////
////        public void minus() {
////            if (count > 0) {
////                --count;
////            }
////        }
////
////        public int get() {
////            return count;
////        }
////
////        public boolean isRemained() {
////            return count > 0;
////        }
////    }
////
////    protected static class TotalScore {
////
////        private int totalScore;
////
////        public TotalScore(int totalScore) {
////            this.totalScore = totalScore;
////        }
////
////        public static TotalScore ofInitialStrike() {
////            return new TotalScore(10);
////        }
////
////        public int get() {
////            return totalScore;
////        }
////
////        public void appendScoreOf(BonusShotCount bonusCount, Frame frame) {
////            if (frame.isThrowFirst()) {
////                totalScore += frame.firstScore();
////                bonusCount.minus();
////            }
////
////            if (frame.isThrowSecond() && bonusCount.isRemained()) {
////                totalScore += frame.secondScore();
////                bonusCount.minus();
////            }
////        }
////
////        public OptionalInt getAsOptional() {
////            return OptionalInt.of(totalScore);
////        }
////    }
//}
