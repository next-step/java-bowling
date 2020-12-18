package bowling.domain.frame.presenter;

import java.util.List;

public class NormalFramePresenter {
    public static final String STRIKE = "X";
    public static final String SPARE = "/";
    public static int TEN_FALLEN_PINS = 10;
    
    public static String present(final List<Integer> fallenPins) {
        if (fallenPins.isEmpty()) {
            return "";
        }

        if (fallenPins.size() == 1) {
            return presentFirstPitching(fallenPins);
        }
        return presentSecondPitching(fallenPins);
    }

    public static String presentFirstPitching(final List<Integer> fallenPins) {
        final int first = fallenPins.get(0);
        return SymbolMap.getSymbol(first);
    }

    private static String presentSecondPitching(final List<Integer> fallenPins) {
        final int first = fallenPins.get(0);
        final int second = fallenPins.get(1);
        if (first + second == TEN_FALLEN_PINS) {
            return SymbolMap.getSymbol(first) + SPARE;
        }
        return SymbolMap.getSymbol(first) + SymbolMap.getSymbol(second);
    }
}
