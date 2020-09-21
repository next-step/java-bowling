package bowling.view;

import bowling.domain.state.Continue;
import bowling.domain.state.Miss;
import bowling.domain.state.Spare;
import bowling.domain.state.State;

public enum Symbol {
    STRIKE("X") {
        @Override
        public String getSymbol(State state) {
            return STRIKE.toString();
        }
    },
    SPARE("|/") {
        @Override
        public String getSymbol(State state) {
            String spareFirst = ((Spare) state).getFirstPin().toString();
            if(checkGutter(spareFirst))
                spareFirst = GUTTER.toString();
            return spareFirst + SPARE.toString();
        }
    },
    GUTTER("-") {
        @Override
        public String getSymbol(State state) {
            return GUTTER.toString();
        }
    },
    MISS("|") {
        @Override
        public String getSymbol(State state) {
            String missFirst = ((Miss) state).getFirstPin().toString();
            String missSecond = ((Miss) state).getSecondPin().toString();

            if(checkGutter(missFirst))
                missFirst = GUTTER.toString();
            if(checkGutter(missSecond))
                missSecond = GUTTER.toString();

            return missFirst + MISS.toString() + missSecond;
        }
    },
    READY("") {
        @Override
        public String getSymbol(State state) {
            return READY.toString();
        }
    },
    CONTINUE("") {
        @Override
        public String getSymbol(State state) {
            String continueFirst = ((Continue) state).getFirstPin().toString();

            if(checkGutter(continueFirst))
                continueFirst = Symbol.GUTTER.toString();

            return continueFirst;
        }
    },
    ALL_GUTTER("-|-") {
        @Override
        public String getSymbol(State state) {
            return ALL_GUTTER.toString();
        }
    };

    private String symbol;

    Symbol(String symbol) {
        this.symbol = symbol;
    }

    public abstract String getSymbol(State state);

    @Override
    public String toString() {
        return symbol;
    }

    private static boolean checkGutter(String felledPin) {
        return Integer.parseInt(felledPin) == 0;
    }

}
