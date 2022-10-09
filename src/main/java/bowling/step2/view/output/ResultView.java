package bowling.step2.view.output;

import bowling.step2.domain.frame.Frame;
import bowling.step2.dto.PlayerDTO;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ResultView {
    private static final String BOARD_BASE_DISPLAY = "| NAME  |  01   |  02   |  03   |  04   |  05   |  06   |  07   |  08   |  09   |  10   |";
    private static final int MAX_LENGTH_OF_PER_FRAME_DISPLAY = 7;
    private static final String DELIMITER = "|";
    private static final String EMPTY = "";
    
    public static void printPlayerFramesDisplay(final PlayerDTO playerDTO) {
        System.out.println(BOARD_BASE_DISPLAY);
        System.out.println(getPlayerResultDisplayFormat(playerDTO));
    }
    
    private static String getPlayerResultDisplayFormat(final PlayerDTO playerDTO) {
        return IntStream.range(0, 10)
                .mapToObj(index -> getFrameDisplayFormat(playerDTO.getFrames(), index))
                .collect(Collectors.joining(DELIMITER, DELIMITER + parseFrameDisplayPrintFormat(playerDTO.getPlayerName()) + DELIMITER, DELIMITER));
    }
    
    private static String getFrameDisplayFormat(final List<Frame> frames, final int index) {
        if (frames.size() - 1 < index) {
            return parseFrameDisplayPrintFormat("");
        }
        
        final Frame frame = frames.get(index);
        return parseFrameDisplayPrintFormat(frame.display());
    }
    
    private static String parseFrameDisplayPrintFormat(final String display) {
        return String.format(String.format("%%%ds%s%%%ds", getLeftSpaceLength(display), display, getRightSpaceLength(display)), EMPTY, EMPTY);
    }
    
    private static int getRightSpaceLength(final String display) {
        return getLeftSpaceLength(display) + (MAX_LENGTH_OF_PER_FRAME_DISPLAY - display.length()) % 2;
    }
    
    private static int getLeftSpaceLength(final String display) {
        return (MAX_LENGTH_OF_PER_FRAME_DISPLAY - display.length()) / 2;
    }
}
