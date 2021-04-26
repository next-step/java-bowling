package bowling.view;

import bowling.dto.FrameDTO;
import bowling.dto.FramesDTO;
import bowling.dto.PlayerDTO;
import bowling.dto.StateDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static bowling.view.StateView.*;

public class ResultView {
    private static final String FRAME_HEADER = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
    private static final String NAME_FORMAT = "  %-3s ";
    private static final String TWO_PITCH_FORMAT = "  %-4s";
    private static final String THREE_PITCH_FORMAT = " %-5s";
    private static final int THREE_PITCH_STATE_LENGTH = 5;

    private ResultView() {}

    private static void printFrames(String name, FramesDTO framesDTO) {
        List<String> frames = new ArrayList<>();
        frames.add(String.format(NAME_FORMAT, name));
        for (FrameDTO frameDTO : framesDTO.frames()) {
            frames.add(formatState(frameDTO));
        }
        System.out.println(joinFrames(frames));
    }

    private static String formatState(FrameDTO frameDTO) {
        String state = joinStates(frameDTO.stateDTOList());
        String STATE_FORMAT = TWO_PITCH_FORMAT;
        if (state.length() >= THREE_PITCH_STATE_LENGTH) {
            STATE_FORMAT = THREE_PITCH_FORMAT;
        }
        return String.format(STATE_FORMAT, state);
    }

    private static String joinStates(List<StateDTO> stateDTOList) {
        List<String> states = new ArrayList<>();
        for(StateDTO stateDTO : stateDTOList){
            states.add(StateView.valueOf(stateDTO.state()).stateView(stateDTO));
        }
        return states.stream()
                .collect(Collectors.joining(DELIMITER));
    }

    private static String joinFrames(List<String> frames) {
        String result = DELIMITER;
        result += frames.stream().collect(Collectors.joining(DELIMITER));
        result += DELIMITER;
        return result;
    }

    public static void printBoard(PlayerDTO playerDTO) {
        System.out.println(FRAME_HEADER);
        printFrames(playerDTO.name(), playerDTO.framesDTO());
        System.out.println();
    }
}
