package bowling.ui;

import bowling.domain.ScoreType;
import bowling.dto.FrameDto;
import bowling.dto.FramesDto;
import bowling.dto.PlayerDto;
import bowling.dto.ShotScoreDto;

import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OutputView {
    private static final String FRAME_HEADER_STRING = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
    private static final String PLAYER_FORMAT = "|  %3s |";
    private static final String FRAME_BODY_FORMAT = " %-5s|";
    private static final String FRAME_SCORE_FORMAT = "  %-3s |";
    private static final String FRAME_SCORE_FIRST = "|      |";
    private static final String SHOT_DELIMITER = "|";
    private static final String STRIKE = "X";
    private static final String SPARE = "/";
    private static final String GUTTER = "-";
    private static final int MAX = 10;

    public void printFrame(PlayerDto playerDto) {
        System.out.println(FRAME_HEADER_STRING);

        System.out.print(String.format(PLAYER_FORMAT, playerDto.getName()));

        FramesDto frames = playerDto.getFrames();
        getParsedFrameStringStream(frames)
                .map(frameString -> String.format(FRAME_BODY_FORMAT, frameString))
                .forEach(System.out::print);
        System.out.println();
        System.out.print(FRAME_SCORE_FIRST);
        getParsedFrameScoreStream(frames)
                .map(scoreString -> String.format(FRAME_SCORE_FORMAT, scoreString))
                .forEach(System.out::print);
        System.out.println();
        System.out.println();
    }

    private Stream<String> getParsedFrameStringStream(FramesDto frames) {
        return Stream.concat(getFrameStringStream(frames),
                getEmptyStringStream(frames));
    }

    private Stream<String> getFrameStringStream(FramesDto frames) {
        return frames.getFrames()
                .stream()
                .map(this::parseFrame);
    }

    private Stream<String> getEmptyStringStream(FramesDto frames) {
        return Stream.generate(() -> "")
                .limit(MAX - frames.size());
    }

    private String parseFrame(FrameDto frameDto) {
        return frameDto.getShotScores()
                .stream()
                .map(this::parseScore)
                .collect(Collectors.joining(SHOT_DELIMITER));
    }

    private String parseScore(ShotScoreDto v) {
        if (v.getScoreType().equals(ScoreType.STRIKE)) {
            return STRIKE;
        }
        if (v.getScoreType().equals(ScoreType.SPARE)) {
            return SPARE;
        }
        if (v.getScoreType().equals(ScoreType.GUTTER)) {
            return GUTTER;
        }

        return Integer.toString(v.getScore());
    }

    private Stream<String> getParsedFrameScoreStream(FramesDto frames) {
        return Stream.concat(getFrameScoreStream(frames),
                getEmptyStringStream(frames));
    }

    private Stream<String> getFrameScoreStream(FramesDto frames) {
        return frames.getFrames()
                .stream()
                .map(FrameDto::getScore)
                .map(Objects::toString)
                .map(v -> v.equals("null")? "" : v);
    }
}
