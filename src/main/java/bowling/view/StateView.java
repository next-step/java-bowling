package bowling.view;

import bowling.dto.StateDTO;

public enum StateView {
    READY("") {
        @Override
        public String stateView(StateDTO stateDTO) {
            return READY.symbol;
        }
    },
    CONTINUE("") {
        @Override
        public String stateView(StateDTO stateDTO) {
            int pins = stateDTO.pins().get(0);
            return printPins(pins);
        }
    },
    MISS("") {
        @Override
        public String stateView(StateDTO stateDTO) {
            int firstPins = stateDTO.pins().get(0);
            int secondPins = stateDTO.pins().get(1);
            String result = printPins(firstPins) + DELIMITER + printPins(secondPins);
            return result;
        }
    },
    SPARE("/") {
        @Override
        public String stateView(StateDTO stateDTO) {
            int firstPins = stateDTO.pins().get(0);
            String result = printPins(firstPins) + DELIMITER + SPARE.symbol;
            return result;
        }
    },
    STRIKE("X") {
        @Override
        public String stateView(StateDTO stateDTO) {
            return STRIKE.symbol;
        }
    };

    private static final int MIN_PINS = 0;
    private static final String GUTTER = "-";
    public static final String DELIMITER = "|";
    private String symbol;

    StateView(String symbol) {
        this.symbol = symbol;
    }

    private static String printPins(int pins) {
        String result = String.valueOf(pins);
        if (pins == MIN_PINS) {
            result = GUTTER;
        }
        return result;
    }

    public abstract String stateView(StateDTO stateDTO);
}
