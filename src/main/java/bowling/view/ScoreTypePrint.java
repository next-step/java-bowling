//package bowling.view;
//
//import bowling.entity.frame.NormalFrame;
//import bowling.entity.Pin;
//import bowling.entity.score.Score;
//
//import java.util.Arrays;
//
//public enum ScoreTypePrint {
//    STRIKE("Strike") {
//        @Override
//        public String printValue(Score score) {
//            return "X";
//        }
//    },
//    SPARE("Spare") {
//        @Override
//        public String printValue(Score score) {
//            return "|/";
//        }
//    },
//    NORMAL("Normal") {
//        @Override
//        public String printValue(Score score) {
//            if (score.score().pin() == Pin.MIN_PIN_COUNT) {
//                return "-";
//            }
//
//            return String.valueOf(score.score().pin());
//        }
//    },
//    MISS("Miss") {
//        @Override
//        public String printValue(Score score) {
//            if (score.score().pin() == Pin.MIN_PIN_COUNT) {
//                return "-";
//            }
//
//            return "|" + score.score().pin();
//        }
//    },
//    NONE("None") {
//        @Override
//        public String printValue(Score score) {
//            return "";
//        }
//    },
//    NOT_MATCH("NotMatch") {
//        @Override
//        public String printValue(Score score) {
//            return "";
//        }
//    };
//
//    private final String score;
//
//    ScoreTypePrint(String score) {
//        this.score = score;
//    }
//
//    abstract public String printValue(Score score);
//
//    public static String getPrintValue(NormalFrame normalFrame) {
//        String firstScore = Arrays.stream(ScoreTypePrint.values())
//                .filter(scoreTypePrint -> scoreTypePrint.score.equals(normalFrame.firstScore().toString()))
//                .findFirst()
//                .orElse(NOT_MATCH).printValue(normalFrame.firstScore());
//
//        String secondScore = Arrays.stream(ScoreTypePrint.values())
//                .filter(secondScoreTypePrint -> secondScoreTypePrint.score.equals(normalFrame.secondScore().toString()))
//                .findFirst()
//                .orElse(NOT_MATCH).printValue(normalFrame.secondScore());
//
//        return firstScore + secondScore;
//    }
//
//    public static String getPrintValue(Score Score) {
//        return Arrays.stream(ScoreTypePrint.values())
//                .filter(scoreTypePrint -> scoreTypePrint.score.equals(Score.toString()))
//                .findFirst()
//                .orElse(NOT_MATCH).printValue(Score);
//    }
//}
