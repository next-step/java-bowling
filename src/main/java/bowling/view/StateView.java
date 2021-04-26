package bowling.view;

import bowling.dto.StateDTO;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class StateView {
    private static final String EMPTY = "";
    private static final String DELIMITER = "|";
    private static final String GUTTER = "-";
    private static final String Spare = "/";
    private static final String Strike = "X";
    private static final String READY = "Ready";
    private static final String CONTINUE = "Continue";
    private static final String MISS = "Miss";
    private static final String SPARE = "Spare";
    private static final String STRIKE = "Strike";
    private static final int MIN_PINS = 0;

    public static final String TWO_PITCH_FORMAT = "  %-4s";
    public static final String THREE_PITCH_FORMAT = " %-5s";
    public static final Map<String, Function<StateDTO, String>> stateMap = new HashMap<>();

    static {
        stateMap.put(READY, StateView::readyView);
        stateMap.put(CONTINUE, StateView::continueView);
        stateMap.put(MISS, StateView::missView);
        stateMap.put(SPARE, StateView::spareView);
        stateMap.put(STRIKE, StateView::strikeView);
    }

    private static String printPins(int pins) {
        String result = String.valueOf(pins);
        if (pins == MIN_PINS) {
            result = GUTTER;
        }
        return result;
    }

    public static String readyView(StateDTO stateDTO) {
        return EMPTY;
    }

    public static String continueView(StateDTO stateDTO) {
        int pins = stateDTO.pins().get(0);
        return printPins(pins);
    }

    public static String missView(StateDTO stateDTO) {
        int firstPins = stateDTO.pins().get(0);
        int secondPins = stateDTO.pins().get(1);
        String result = printPins(firstPins) + DELIMITER + printPins(secondPins);
        return result;
    }

    public static String spareView(StateDTO stateDTO) {
        int firstPins = stateDTO.pins().get(0);
        String result = printPins(firstPins) + DELIMITER + Spare;
        return result;
    }

    public static String strikeView(StateDTO stateDTO) {
        return Strike;
    }
}
