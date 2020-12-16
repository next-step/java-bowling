package bowling.view.output;

import java.util.List;

import static bowling.view.output.OutputView.SPARE;

public class NormalFramePresenter {
    public static String present(final List<Integer> fallenPins) {
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
        if (first + second == 10) {
            return SymbolMap.getSymbol(first) + SPARE;
        }
        return SymbolMap.getSymbol(first) + SymbolMap.getSymbol(second);
    }
}
