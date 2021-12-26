package bowling.domain;

import static bowling.domain.Pin.MAX_PIN_COUNT;
import static bowling.domain.Pin.MIN_PIN_COUNT;

public enum State {
    STRIKE("X",true) {
        public String symbol(Pin first, Pin second) {
            return this.getSymbol();
        }
        public State bowl(Pin first, Pin second) {
            if(second.isStrike()) {
                return STRIKE;
            }
            return MISS;
        }
    },
    SPARE("/",true) {
        public String symbol(Pin first, Pin second) {
            return convertValue(first) + "|/";
        }
        public State bowl(Pin first, Pin second) {
            return SPARE;
        }
    },
    MISS("",true) {
        public String symbol(Pin first, Pin second) {
            return convertValue(first) + "|" + convertValue(second);
        }
        public State bowl(Pin first, Pin second) {
            if(first.isGutter() && second.isGutter()) {
                return GUTTER;
            }
            return MISS;
        }
    },
    GUTTER("-",true) {
        public String symbol(Pin first, Pin second) {
            return this.getSymbol();
        }
        public State bowl(Pin first, Pin second) {
            if (!first.isGutter() || !second.isGutter()) {
                return MISS;
            }
            return GUTTER;
        }
    },
    FIRST("",false) {
        public String symbol(Pin first, Pin second) {
            if(first.isGutter()) {
                return "-";
            }
            return String.valueOf(second.getFallenPinCount());
        }
        public State bowl(Pin first, Pin second) {
            if (first.isSpare(second)) {
                return SPARE;
            }
            if (first.isGutter() && second.isGutter()) {
                return GUTTER;
            }
            return MISS;
        }
    },
    READY("",false) {
        public String symbol(Pin first, Pin second) {
            return "";
        }
        public State bowl(Pin first, Pin second) {
            if (second.isStrike()) {
                return STRIKE;
            }
            return FIRST;
        }
    }
    ;

    private final String symbol;
    private final boolean isEnd;

    public abstract State bowl(Pin first, Pin second);
    public abstract String symbol(Pin first, Pin second);

    State(String symbol, boolean isEnd) {
        this.symbol = symbol;
        this.isEnd = isEnd;
    }

    public String getSymbol() {
        return this.symbol;
    }

    public boolean isEnd() {
        return this.isEnd;
    }

    String convertValue(Pin pin) {
        if (pin.isGutter()) {
            return "-";
        }
        return String.valueOf(pin.getFallenPinCount());
    }

    public static String of(int pin) {
        if(pin == MAX_PIN_COUNT) {
            return STRIKE.symbol;
        }

        if (pin == MIN_PIN_COUNT) {
            return GUTTER.symbol;
        }

        return String.valueOf(pin);
    }

}
