package bowling.view;

import bowling.domain.state.Continue;
import bowling.domain.state.Miss;
import bowling.domain.state.Spare;
import bowling.domain.state.State;

public enum Symbol {
    Strike("X"),
    Spare("|/"),
    Gutter("-"),
    Miss("|"),
    Ready(""),
    Continue(""),
    AllGutter("-|-");

    private String symbol;

    Symbol(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return symbol;
    }


    public static String getSpareSymbol(State state) {
        String spareFirst = ((Spare) state).getFirstPin().toString();
        if(checkGutter(spareFirst))
            spareFirst = Symbol.Gutter.toString();
        return spareFirst + Symbol.Spare.toString();
    }

    public static String getMissSymbol(State state) {
        String missFirst = ((Miss) state).getFirstPin().toString();
        String missSecond = ((Miss) state).getSecondPin().toString();

        if(checkGutter(missFirst))
            missFirst = Symbol.Gutter.toString();
        if(checkGutter(missSecond))
            missSecond = Symbol.Gutter.toString();

        return missFirst + Symbol.Miss.toString() + missSecond;
    }

    public static String getContinueSymbol(State state) {
        String continueFirst = ((Continue) state).getFirstPin().toString();

        if(checkGutter(continueFirst))
            continueFirst = Symbol.Gutter.toString();

        return continueFirst;
    }

    private static boolean checkGutter(String felledPin) {
        return Integer.parseInt(felledPin) == 0;
    }

}
