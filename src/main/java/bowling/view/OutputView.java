package bowling.view;

import bowling.domain.frame.ScoreBoard;
import bowling.domain.frame.dto.FrameDTO;
import bowling.domain.frame.dto.ScoreBoardDTO;
import bowling.domain.user.dto.UserDTO;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

public class OutputView {

    private static final String CURRENT_FRAME_TXT = "{0} 프레임 투구 : ";
    private static final String HEADER = "|  NAME  |   01   |   02   |   03   |   04   |   05   |   06   |   07   |   08   |   09   |   10   |";
    private static final String SCORE = "|{0}   |{1}   |{2}   |{3}   |{4}   |{5}   |{6}   |{7}   |{8}   |{9}   | {10}  |";
    private static final String EMPTY_STRING = "";
    private static final String STRING_FORMAT = "%5s";

    private OutputView() {
    }

    public static void print(UserDTO userDTO, ScoreBoardDTO scoreBoardDTO) {
        System.out.println(HEADER);
        String format = MessageFormat.format(SCORE, toScoreArray(userDTO, scoreBoardDTO));
        System.out.println(format);
    }

    private static String[] toScoreArray(UserDTO userDTO, ScoreBoardDTO scoreBoardDTO) {
        List<String> result = new ArrayList<>(Arrays.asList(String.format(STRING_FORMAT, userDTO.getName())));
        scoreBoardDTO.getScoreBoardDTO().stream()
                .map(FrameDTO::getRecord)
                .map(gameResult -> String.format(STRING_FORMAT, gameResult))
                .collect(collectingAndThen(toList(), result::addAll));

        generateEmptyFrame(result);
        return result.stream().toArray(String[]::new);
    }

    private static void generateEmptyFrame(List<String> result) {
        for (int size = result.size(); size < ScoreBoard.END_FRAME_COUNT + 1; size++) {
            result.add(String.format(STRING_FORMAT, EMPTY_STRING));
        }
    }

    public static void printCurrentFrame(int size) {
        System.out.println();
        System.out.print(MessageFormat.format(CURRENT_FRAME_TXT, size));
    }
}
