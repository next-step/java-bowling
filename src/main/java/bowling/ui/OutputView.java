package bowling.ui;

import bowling.dto.FrameDto;
import bowling.dto.PlayerDto;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class OutputView {
    private static final String FRAME_HEADER_STRING = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
    private static final String PLAYER_FORMAT = "|  %3s |";
    private static final String FRAME_BODY_FORMAT = " %-5s|";
    private static final String FRAME_SCORE_FORMAT = "  %-3s |";
    private static final String FRAME_SCORE_FIRST = "|      |";
    private static final String SHOT_DELIMITER = "|";
    private static final int MAX = 10;

    public void printFrame(PlayerDto player) {
        System.out.println(FRAME_HEADER_STRING);
        printFrameShots(player);
        printFrameScores(player);
        System.out.println();
    }

    private void printFrameScores(PlayerDto player) {
        System.out.print(FRAME_SCORE_FIRST);
        getParsedFrameScoreStream(player.getFrames())
                .map(scoreString -> String.format(FRAME_SCORE_FORMAT, scoreString))
                .forEach(System.out::print);
        System.out.println();
    }

    private void printFrameShots(PlayerDto player) {
        System.out.print(String.format(PLAYER_FORMAT, player.getName()));
        getParsedFrameStringStream(player.getFrames())
                .map(frameString -> String.format(FRAME_BODY_FORMAT, frameString))
                .forEach(System.out::print);
        System.out.println();
    }

    private Stream<String> getParsedFrameStringStream(List<FrameDto> frames) {
        return Stream.concat(getFrameStringStream(frames),
                getEmptyStringStream(frames));
    }

    private Stream<String> getFrameStringStream(List<FrameDto> frames) {
        return frames
                .stream()
                .map(this::parseFrameShots);
    }

    private String parseFrameShots(FrameDto frame) {
        return String.join(SHOT_DELIMITER, frame.getShotScores());
    }

    private Stream<String> getParsedFrameScoreStream(List<FrameDto> frames) {
        return Stream.concat(getFrameScoreStream(frames),
                getEmptyStringStream(frames));
    }

    private Stream<String> getFrameScoreStream(List<FrameDto> frames) {
        return frames
                .stream()
                .map(FrameDto::getFrameScore)
                .map(Objects::toString)
                .map(v -> v.equals("null")? "" : v);
    }

    private Stream<String> getEmptyStringStream(List<FrameDto> frames) {
        return Stream.generate(() -> "")
                .limit(MAX - frames.size());
    }
}
