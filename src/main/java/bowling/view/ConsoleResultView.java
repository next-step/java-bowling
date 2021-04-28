package bowling.view;

import bowling.dto.FrameStateDto;
import bowling.dto.FramesDto;
import bowling.dto.PlayerDto;
import bowling.dto.ScoreDto;

import java.util.List;
import java.util.stream.Collectors;

public class ConsoleResultView implements ResultView {

    private static final String DELIMITER = "|";
    private static final String WHITE_SPACE = " ";
    private static final String ZERO_PREFIX = "0";
    private static final int ELEMENT_LENGTH = 6;
    private static final int TOTAL_FRAMES = 10;

    @Override
    public void printStateOfPlayer(PlayerDto playerDto) {
        printHeader();
        printBody(playerDto);
    }

    private void printHeader() {
        StringBuilder stringBuilder = new StringBuilder(DELIMITER);
        stringBuilder.append(alignCenter("NAME")).append(DELIMITER);

        for (int i = 1; i <= TOTAL_FRAMES; i++){
            stringBuilder.append(alignCenter(frameNumber(i))).append(DELIMITER);
        }

        printStringBuilder(stringBuilder);
    }

    private void printBody(PlayerDto playerDto) {
        FramesDto framesDto = playerDto.getFramesDto();

        List<String> frameStates = framesDto.getStates()
                                            .stream()
                                            .map(FrameStateDto::getState)
                                            .collect(Collectors.toList());

        List<String> frameScores = framesDto.getScores()
                                            .stream()
                                            .map(ScoreDto::getScore)
                                            .collect(Collectors.toList());

        printState(playerDto.getPlayerName(), frameStates);
        printScore(frameScores);
    }

    private String frameNumber(int i) {
        if (i == TOTAL_FRAMES) {
            return String.valueOf(TOTAL_FRAMES);
        }

        return ZERO_PREFIX + i;
    }

    private void printState(String playerName, List<String> frameStates) {
        StringBuilder stringBuilder = new StringBuilder(DELIMITER);
        stringBuilder.append(alignCenter(playerName)).append(DELIMITER);

        frameStates.forEach(frameState -> {
            String centeredRecord = alignCenter(String.join(DELIMITER, frameState));
            stringBuilder.append(centeredRecord).append(DELIMITER);
        });

        printStringBuilder(stringBuilder);
    }

    private void printScore(List<String> frameScores) {
        StringBuilder stringBuilder = new StringBuilder(DELIMITER);
        stringBuilder.append(alignCenter("")).append(DELIMITER);

        frameScores.forEach(frameScore -> {
            String centeredRecord = alignCenter(String.join(DELIMITER, frameScore));
            stringBuilder.append(centeredRecord).append(DELIMITER);
        });

        printStringBuilder(stringBuilder);
    }

    private void printStringBuilder(StringBuilder stringBuilder) {
        System.out.println(stringBuilder.toString());
    }

    private String alignCenter(String string) {
        int stringLength = string.length();

        int rightPadding = (ELEMENT_LENGTH - stringLength) / 2;
        int leftPadding = ELEMENT_LENGTH - stringLength - rightPadding;

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < leftPadding; i++){
            stringBuilder.append(WHITE_SPACE);
        }
        stringBuilder.append(string);
        for (int i = 0; i < rightPadding; i++){
            stringBuilder.append(WHITE_SPACE);
        }

        return stringBuilder.toString();
    }

}
