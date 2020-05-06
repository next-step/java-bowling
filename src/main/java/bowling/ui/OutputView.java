package bowling.ui;

import bowling.dto.FrameShotDto;
import bowling.dto.PlayerDto;
import bowling.dto.PlayersDto;

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

    public void printFrame(PlayersDto players) {
        System.out.println(FRAME_HEADER_STRING);
        for (PlayerDto player : players.getPlayers()) {
            printFrameShots(player);
            printFrameScores(player);
        }
        System.out.println();
    }

    private void printFrameScores(PlayerDto player) {
        System.out.print(FRAME_SCORE_FIRST);
        getParsedFrameScoreStream(player.getFrameScores())
                .map(scoreString -> String.format(FRAME_SCORE_FORMAT, scoreString))
                .forEach(System.out::print);
        System.out.println();
    }

    private void printFrameShots(PlayerDto player) {
        System.out.print(String.format(PLAYER_FORMAT, player.getName()));
        getParsedFrameStringStream(player.getFrameShots())
                .map(frameString -> String.format(FRAME_BODY_FORMAT, frameString))
                .forEach(System.out::print);
        System.out.println();
    }

    private Stream<String> getParsedFrameStringStream(List<FrameShotDto> frames) {
        return Stream.concat(getFrameStringStream(frames),
                getEmptyStringStream(frames.size()));
    }

    private Stream<String> getFrameStringStream(List<FrameShotDto> frames) {
        return frames
                .stream()
                .map(this::parseFrameShots);
    }

    private String parseFrameShots(FrameShotDto frame) {
        return String.join(SHOT_DELIMITER, frame.getShots());
    }

    private Stream<String> getParsedFrameScoreStream(List<Integer> frameScores) {
        return Stream.concat(getFrameScoreStream(frameScores),
                getEmptyStringStream(frameScores.size()));
    }

    private Stream<String> getFrameScoreStream(List<Integer> frameScores) {
        return frameScores
                .stream()
                .map(Objects::toString);
    }

    private Stream<String> getEmptyStringStream(int size) {
        return Stream.generate(() -> "")
                .limit(MAX - size);
    }
}
