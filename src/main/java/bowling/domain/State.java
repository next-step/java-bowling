package bowling.domain;

public enum State {
    STRIKE("X",true) {
        public State bowl(Pin first, Pin second) {
            if(second.isStrike()) {
                return STRIKE;
            }
            return MISS;
        }
        public Score score(Pin pin) {
            return Score.strike();
        }
    },
    SPARE("/",true) {
        public State bowl(Pin first, Pin second) {
            return SPARE;
        }
        public Score score(Pin pin) {
            return pin.toScore(ScoreBonus.oneMore());
        }
    },
    MISS("",true) {
        public State bowl(Pin first, Pin second) {
            if(first.isGutter() && second.isGutter()) {
                return GUTTER;
            }
            return MISS;
        }
        public Score score(Pin pin) {
            return pin.toScoreVisible();
        }
    },
    GUTTER("-",true) {
        public State bowl(Pin first, Pin second) {
            if (!first.isGutter() || !second.isGutter()) {
                return MISS;
            }
            return GUTTER;
        }
        public Score score(Pin pin) {
            return pin.toScoreVisible();
        }
    },
    FIRST("",false) {
        public State bowl(Pin first, Pin second) {
            if (first.isSpare(second)) {
                return SPARE;
            }
            if (first.isGutter() && second.isGutter()) {
                return GUTTER;
            }
            return MISS;
        }
        public Score score(Pin pin) {
            return pin.toScore(ScoreBonus.oneMore());
        }
    },
    READY("",false) {
        public State bowl(Pin meaningLess, Pin pin) {
            if (pin.isStrike()) {
                return STRIKE;
            }
            return FIRST;
        }
        public Score score(Pin pin) {
            return Score.init();
        }
    }
    ;

    private final String symbol;
    private final boolean isEnd;

    public abstract State bowl(Pin first, Pin second);
    public abstract Score score(Pin pin);

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

    public String getSymbol(Pin pin) {
        if(this == FIRST || this == MISS) {
            if (pin.isGutter()) {
                return GUTTER.symbol;
            }
            return String.valueOf(pin);
        }
        return this.symbol;
    }

}
