package bowling.view;

import bowling.dto.PlayerDto;
import bowling.dto.RecordsDto;

import java.util.List;

public class ConsoleResultView implements ResultView {

    private static final String DELIMITER = "|";
    private static final String WHITE_SPACE = " ";
    private static final int ELEMENT_LENGTH = 6;
    private static final int TOTAL_FRAMES = 10;

    @Override
    public void printStateOfPlayer(PlayerDto playerDto) {
        System.out.println(buildHeader());
        System.out.println(buildBody(playerDto));
    }

    private String buildHeader() {
        StringBuilder stringBuilder = new StringBuilder(DELIMITER);
        stringBuilder.append(alignMiddle("NAME")).append(DELIMITER);

        for (int i = 1; i <= TOTAL_FRAMES; i++){
            stringBuilder.append(alignMiddle(frameNumber(i))).append(DELIMITER);
        }

        return stringBuilder.toString();
    }

    private String frameNumber(int i) {
        return i < TOTAL_FRAMES ? "0" + i : String.valueOf(i);
    }

    private String buildBody(PlayerDto playerDto) {
        StringBuilder stringBuilder = new StringBuilder(DELIMITER);
        stringBuilder.append(alignMiddle(playerDto.getPlayerName())).append(DELIMITER);

        List<RecordsDto> frameRecords = playerDto.getBowlingDto().getFrameRecords();

        frameRecords.forEach(recordsDto -> {
            String record = alignMiddle(String.join(DELIMITER, recordsDto.getRecords()));
            stringBuilder.append(record).append(DELIMITER);
        });

        for (int i = 0; i < TOTAL_FRAMES - frameRecords.size(); i++){
            stringBuilder.append(alignMiddle("")).append(DELIMITER);
        }

        return stringBuilder.toString();
    }

    private String alignMiddle(String string) {
        int stringLength = string.length();

        int leftPadding = (ELEMENT_LENGTH - stringLength) / 2;
        int rightPadding = ELEMENT_LENGTH - stringLength - leftPadding;

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
