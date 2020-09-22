package bowling.view;

import bowling.model.score.Scores;
import bowling.model.frame.dto.FramesDto;
import bowling.model.Player;
import bowling.model.Result;
import bowling.model.scoreboard.ScoreBoardDto;
import bowling.util.StringUtils;

import java.util.Map;
import java.util.stream.Collectors;

public class ResultView {
    private final static String HEADER = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
    public static final String DELIMITER = "|";
    public static final int PLAYER_LENGTH = 6;
    public static final int RESULT_LENGTH = 5;

    public void printHeader() {
        System.out.println(HEADER);
    }

    public void printFrames(Player player, FramesDto frames) {
        System.out.printf("%s%s%s", DELIMITER, StringUtils.center(player.getName(), PLAYER_LENGTH), DELIMITER);

        frames.getFrames().stream()
                .map(frameDto -> frameDto.getResults().stream()
                    .map(Result::toString)
                    .collect(Collectors.joining(DELIMITER)))
                .forEach(str -> System.out.printf("%s %s", StringUtils.center(str, RESULT_LENGTH), DELIMITER));

        System.out.println();
    }

    public void printScores(Scores scores) {
        System.out.printf("%s%s%s", DELIMITER, StringUtils.center("", PLAYER_LENGTH), DELIMITER);

        scores.getScores()
                .forEach(score -> System.out.printf("%s %s", StringUtils.center(score.toString(), RESULT_LENGTH), DELIMITER));

        System.out.println();
    }

    public void printScoreBoard(ScoreBoardDto scoreBoard) {
        Map<Player, FramesDto> playerFrames = scoreBoard.getFrames();
        Map<Player, Scores> scores = scoreBoard.getScores();
        playerFrames.forEach(((player, frames) -> {
            printFrames(player, frames);
            printScores(scores.get(player));
        }));
    }

    public void printError(Throwable throwable) {
        System.out.println("오류발생: " + throwable.getMessage());
    }
}
