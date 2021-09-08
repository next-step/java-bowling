package step2.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum BowLingScoreView {
    STRIKE ("X") { public boolean apply(int bowlingScore, int totalScore, int tryNumber) {return bowlingScore == 10 && tryNumber == 1;}},
    SPARE ("/") { public boolean apply(int bowlingScore, int totalScore, int tryNumber) {return totalScore == 10 && tryNumber >= 2;}},
    GUTTER ("-") { public boolean apply(int bowlingScore, int totalScore, int tryNumber) {return bowlingScore == 0 && tryNumber == 1;}},
    MISS ("-") { public boolean apply(int bowlingScore, int totalScore, int tryNumber) {return bowlingScore == 0 && tryNumber >= 2;}},
    SCORE ("SCORE") { public boolean apply(int bowlingScore, int totalScore, int tryNumber) {return true;}};

    private final String symbol;

    BowLingScoreView(String symbol) {
        this.symbol = symbol;
    }

    public abstract boolean apply(int bowlingScore, int totalScore, int tryNumber);

    public static List<String> transferBowlingScoreViewFormat(List<Integer> frame) {
        List<String> result = new ArrayList<>();
        int totalScore = 0;
        int tryNumber = 0;
        int bowlingScore = 0;
        for (int i = 0; i < frame.size(); i++) {
            tryNumber++;
            bowlingScore = frame.get(i);
            totalScore += bowlingScore;

            result.add(transferBowlingScoreToBowlingScoreSymbol(bowlingScore, totalScore, tryNumber));
        }
        return result;
    }

    private static String transferBowlingScoreToBowlingScoreSymbol(int bowlingScore, int totalScore, int tryNumber) {
        BowLingScoreView bowLingScoreSymbol = Arrays.stream(values())
                .filter(bowLingScoreView -> bowLingScoreView.apply(bowlingScore, totalScore, tryNumber))
                .findFirst()
                .orElse(SCORE);

        if (bowLingScoreSymbol.equals(BowLingScoreView.SCORE)) {
            return Integer.toString(bowlingScore);
        }
        return bowLingScoreSymbol.toString();
    }

    @Override
    public String toString() {
        return symbol;
    }


}
