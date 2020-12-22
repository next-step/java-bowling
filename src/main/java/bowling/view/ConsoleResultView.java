package bowling.view;

import bowling.domain.Frames;
import bowling.domain.Pitching;
import bowling.dto.PlayerDto;
import bowling.dto.BowlingGameDto;
import bowling.dto.FrameDto;
import bowling.dto.FramesDto;

import java.util.EnumMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ConsoleResultView implements ResultView {
    private static final String DELIMITER = "|";
    private static final int CELL_WIDTH = 6;
    private static final String NAME_LABEL = "NAME";
    private static final String FRAME_NO_FORMAT = "%02d";
    private static final String EMPTY_VALUE = "";
    private final Map<Pitching, String> stringByPitching;

    public ConsoleResultView() {
        stringByPitching = new EnumMap<>(Pitching.class);
        stringByPitching.put(Pitching.GUTTER, "-");
        stringByPitching.put(Pitching.ONE_PIN, "1");
        stringByPitching.put(Pitching.TWO_PINS, "2");
        stringByPitching.put(Pitching.THREE_PINS, "3");
        stringByPitching.put(Pitching.FOUR_PINS, "4");
        stringByPitching.put(Pitching.FIVE_PINS, "5");
        stringByPitching.put(Pitching.SIX_PINS, "6");
        stringByPitching.put(Pitching.SEVEN_PINS, "7");
        stringByPitching.put(Pitching.EIGHT_PINS, "8");
        stringByPitching.put(Pitching.NINE_PINS, "9");
        stringByPitching.put(Pitching.STRIKE, "X");
        stringByPitching.put(Pitching.SPARE, "/");
    }

    @Override
    public void print(BowlingGameDto bowlingGameDto) {
        StringBuilder resultBuilder = new StringBuilder();
        appendHeader(resultBuilder);
        appendBody(bowlingGameDto, resultBuilder);
        System.out.println(resultBuilder.toString());
    }

    private void appendHeader(StringBuilder resultBuilder) {
        appendNameLabel(resultBuilder);
        appendFrameNo(resultBuilder);
        resultBuilder.append(System.lineSeparator());
    }

    private void appendNameLabel(StringBuilder resultBuilder) {
        String formattedNameLabel = centerString(NAME_LABEL);
        resultBuilder.append(DELIMITER).append(formattedNameLabel).append(DELIMITER);
    }

    private void appendFrameNo(StringBuilder resultBuilder) {
        IntStream.rangeClosed(1, Frames.MAX_FRAME_SIZE)
                .forEach(frameNo -> {
                    String formattedFrameNo = centerString(String.format(FRAME_NO_FORMAT, frameNo));
                    resultBuilder.append(formattedFrameNo).append(DELIMITER);
                });
    }

    private void appendBody(BowlingGameDto bowlingGameDto, StringBuilder resultBuilder) {
        for (PlayerDto playerDto : bowlingGameDto) {
            appendPlayersStats(playerDto, resultBuilder);
            resultBuilder.append(System.lineSeparator());
        }
    }

    private void appendPlayersStats(PlayerDto playerDto, StringBuilder resultBuilder) {
        appendPlayerName(playerDto, resultBuilder);
        appendResults(playerDto, resultBuilder);
        resultBuilder.append(System.lineSeparator());
        appendScore(playerDto, resultBuilder);
    }

    private void appendPlayerName(PlayerDto playerDto, StringBuilder resultBuilder) {
        String formattedPlayerName = centerString(playerDto.getPlayerName());
        resultBuilder.append(DELIMITER).append(formattedPlayerName).append(DELIMITER);
    }

    private void appendResults(PlayerDto playerDto, StringBuilder resultBuilder) {
        FramesDto frames = playerDto.getFrames();
        IntStream.rangeClosed(1, Frames.MAX_FRAME_SIZE)
                .forEach(frameNo -> {
                    String result = getResult(frames, frameNo);
                    String formattedResult = centerString(result);
                    resultBuilder.append(formattedResult).append(DELIMITER);
                });
    }

    private String getResult(FramesDto frames, int frameNo) {
        FrameDto frameDto = frames.get(frameNo);
        if (frameDto == null) {
            return EMPTY_VALUE;
        }

        return frameDto.getPitchings().stream()
                .map(stringByPitching::get)
                .collect(Collectors.joining(DELIMITER));
    }

    private void appendScore(PlayerDto playerDto, StringBuilder resultBuilder) {
        resultBuilder.append(DELIMITER).append(centerString(EMPTY_VALUE)).append(DELIMITER);
        FramesDto frames = playerDto.getFrames();
        IntStream.rangeClosed(1, Frames.MAX_FRAME_SIZE)
                .forEach(frameNo -> {
                    Integer totalScore = getTotalScore(frames, frameNo);
                    resultBuilder.append(formatScore(totalScore)).append(DELIMITER);
                });
    }

    private Integer getTotalScore(FramesDto frames, int frameNo) {
        FrameDto frameDto = frames.get(frameNo);
        if (frameDto == null) {
            return null;
        }
        return frameDto.getTotalScore();
    }

    private String formatScore(Integer totalScore) {
        if (totalScore == null) {
            return centerString(EMPTY_VALUE);
        }
        return centerString(String.valueOf(totalScore));
    }

    private String centerString(String value) {
        int width = value.length() + ((CELL_WIDTH - value.length()) / 2);
        String formattedValue = String.format("%" + width + "s", value);
        return String.format("%-" + CELL_WIDTH + "s", formattedValue);
    }
}
