package bowling.view;

import bowling.dto.FrameDto;
import bowling.dto.PlayerDto;
import bowling.dto.ScoreBoard;
import bowling.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

public class ResultView {

    public static final String HEADER_SEPARATOR = "|";

    private static final int FIXED_HEADER_SPACE = 10;

    private static final String HEADER_PLAYER_NAME_REPRESENTATION = "NAME";

    private static final int HEADER_MAX_FRAME_COUNT = 10;

    private static final String EMPTY_PIN_COUNT = "";

    public static void printScoreBoard(ScoreBoard scoreBoard) {
        printScoreBoardHeader();
        printScore(scoreBoard);
    }

    private static void printScore(ScoreBoard scoreBoard) {
        PlayerDto playerResult = scoreBoard.playerResult();
        List<FrameDto> framesResult = playerResult.frames();
        System.out.print(HEADER_SEPARATOR);
        System.out.print(StringUtils.center(playerResult.name(),FIXED_HEADER_SPACE));
        for (int i = 1; i <= HEADER_MAX_FRAME_COUNT; i++) {
            System.out.print(HEADER_SEPARATOR);
           String pinCountsInString = EMPTY_PIN_COUNT;
            if(framesResult.size() >= i){
                FrameDto frameDto = framesResult.get(i-1);
                pinCountsInString = frameDto.pinCounts().stream().map(Object::toString).collect(Collectors.joining(", "));
            }
            String formattedPintCount = StringUtils.center(pinCountsInString,FIXED_HEADER_SPACE);
            System.out.print(formattedPintCount);
        }
        System.out.print(HEADER_SEPARATOR);
        printEmptyLine();
    }

    private static void printScoreBoardHeader() {
        System.out.print(HEADER_SEPARATOR);
        System.out.print(StringUtils.center(HEADER_PLAYER_NAME_REPRESENTATION,FIXED_HEADER_SPACE));
        for (int i = 1; i <= HEADER_MAX_FRAME_COUNT; i++) {
            System.out.print(HEADER_SEPARATOR);
            String twoDigitFrameNumber = String.format("%02d", i);
            String formattedFrameNumber = StringUtils.center(twoDigitFrameNumber,FIXED_HEADER_SPACE);
            System.out.print(formattedFrameNumber);
        }
        System.out.print(HEADER_SEPARATOR);
        printEmptyLine();
    }

    public static void printEmptyLine() {
        System.out.println();
    }
}
