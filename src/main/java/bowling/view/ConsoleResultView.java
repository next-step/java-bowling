package bowling.view;

import bowling.dto.PlayerDto;

import java.util.List;

public class ConsoleResultView implements ResultView {

    private static final String DELIMITER = "|";
    private static final String WHITE_SPACE = " ";
    private static final int ELEMENT_LENGTH = 6;
    private static final int FINAL_ELEMENT_LENGTH = 8;
    private static final int TOTAL_FRAMES = 10;

    @Override
    public void printStateOfPlayer(PlayerDto playerDto) {
        System.out.println(buildHeader());
        System.out.println(buildBody(playerDto));
    }

    private String buildHeader() {
        StringBuilder stringBuilder = new StringBuilder(DELIMITER);
        stringBuilder.append(alignCenter("NAME", ELEMENT_LENGTH)).append(DELIMITER);

        for (int i = 1; i < TOTAL_FRAMES; i++){
            stringBuilder.append(alignCenter(frameNumber(i), ELEMENT_LENGTH)).append(DELIMITER);
        }

        stringBuilder.append(alignCenter(frameNumber(10), FINAL_ELEMENT_LENGTH)).append(DELIMITER);

        return stringBuilder.toString();
    }

    private String frameNumber(int i) {
        if (i == TOTAL_FRAMES) {
            return "10";
        }

        return "0" + i;
    }

    private String buildBody(PlayerDto playerDto) {
        StringBuilder stringBuilder = new StringBuilder(DELIMITER);
        stringBuilder.append(alignCenter(playerDto.getPlayerName(), ELEMENT_LENGTH)).append(DELIMITER);

        List<String> frameRecords = playerDto.getFramesDto().getFrames();

        frameRecords.forEach(frameRecord -> {
            String centeredRecord = alignCenter(String.join(DELIMITER, frameRecord), ELEMENT_LENGTH);
            stringBuilder.append(centeredRecord).append(DELIMITER);
        });

        for (int i = 0; i < TOTAL_FRAMES - frameRecords.size() - 1; i++){
            stringBuilder.append(alignCenter("", ELEMENT_LENGTH)).append(DELIMITER);
        }

        stringBuilder.append(alignCenter("", FINAL_ELEMENT_LENGTH)).append(DELIMITER);

        return stringBuilder.toString();
    }

    private String alignCenter(String string, int totalLength) {
        int stringLength = string.length();

        int rightPadding = (totalLength - stringLength) / 2;
        int leftPadding = totalLength - stringLength - rightPadding;

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
