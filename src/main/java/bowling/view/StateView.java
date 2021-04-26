package bowling.view;

import bowling.dto.statedto.FinalStateDTO;
import bowling.dto.statedto.StateDTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

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
    private static final String FINAL_STATE = "FinalState";
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
        stateMap.put(FINAL_STATE, StateView::finalStateView);
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

    public static String finalStateView(StateDTO stateDTO) {
        FinalStateDTO finalStateDTO = (FinalStateDTO) stateDTO;
        List<StateDTO> stateDTOList = finalStateDTO.stateDTOList();
        List<String> states = new ArrayList<>();
        for (StateDTO DTO : stateDTOList) {
            states.add(stateMap.get(DTO.state()).apply(DTO));
        }
        return states.stream()
                .collect(Collectors.joining(DELIMITER));
    }
}
