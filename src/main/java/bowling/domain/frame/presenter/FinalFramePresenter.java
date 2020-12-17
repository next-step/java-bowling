package bowling.domain.frame.presenter;

import java.util.List;

import static bowling.domain.frame.presenter.NormalFramePresenter.*;

public class FinalFramePresenter {
    public static String present(final List<Integer> fallenPins) {
        if (fallenPins.isEmpty()) {
            return "";
        }
        
        if (fallenPins.size() == 1) {
            return presentFirstPitching(fallenPins);
        }

        if (fallenPins.size() == 2) {
            return presentSecondPitching(fallenPins);
        }

        return presentThirdPitching(fallenPins);
    }
    
    private static String presentSecondPitching(final List<Integer> fallenPins) {
        final int first = fallenPins.get(0);
        final int second = fallenPins.get(1);
        if (first == 10) {
            return STRIKE + SymbolMap.getSymbol(second);
        }
        if (first + second == 10) {
            return SymbolMap.getSymbol(first) + SPARE;
        }
        return SymbolMap.getSymbol(first) + SymbolMap.getSymbol(second);
    }

    private static String presentThirdPitching(final List<Integer> fallenPins) {
        final int first = fallenPins.get(0);
        final int second = fallenPins.get(1);
        final int third = fallenPins.get(2);
        
        if (first == 10 && second == 10) {
            return STRIKE + STRIKE + SymbolMap.getSymbol(fallenPins.get(2));
        }

        if (first == 10 && second + third == 10) {
            return STRIKE + SymbolMap.getSymbol(second) + SPARE;
        }

        return SymbolMap.getSymbol(first) + SPARE + SymbolMap.getSymbol(third);
    }
}
