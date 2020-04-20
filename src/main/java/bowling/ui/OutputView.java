package bowling.ui;

import bowling.domain.*;

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

    public void printFrame(Player player) {
        System.out.println(FRAME_HEADER_STRING);

        System.out.print(String.format(PLAYER_FORMAT, player.name()));

        Frames frames = player.frames();
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

    private Stream<String> getParsedFrameStringStream(Frames frames) {
        return Stream.concat(getFrameStringStream(frames),
                getEmptyStringStream(frames));
    }

    private Stream<String> getFrameStringStream(Frames frames) {
        return frames
                .stream()
                .map(this::parseFrame);
    }

    private Stream<String> getEmptyStringStream(Frames frames) {
        return Stream.generate(() -> "")
                .limit(MAX - frames.size());
    }

    private String parseFrame(Frame frame) {
        return frame.shotScores()
                .stream()
                .map(this::parseScore)
                .collect(Collectors.joining(SHOT_DELIMITER));
    }

    private String parseScore(ShotScore scoreType) {
        if (ScoreType.STRIKE.equals(scoreType.scoreType())) {
            return STRIKE;
        }
        if (ScoreType.SPARE.equals(scoreType.scoreType())) {
            return SPARE;
        }
        if (ScoreType.GUTTER.equals(scoreType.scoreType())) {
            return GUTTER;
        }

        return Integer.toString(scoreType.score().score());
    }

    private Stream<String> getParsedFrameScoreStream(Frames frames) {
        return Stream.concat(getFrameScoreStream(frames),
                getEmptyStringStream(frames));
    }

    private Stream<String> getFrameScoreStream(Frames frames) {
        return frames
                .stream()
                .map(Frame::getFrameScore)
                .map(Objects::toString)
                .map(v -> v.equals("null")? "" : v);
    }
}
