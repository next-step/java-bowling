package bowling.view;

import bowling.domain.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import static bowling.view.PinView.AND;
import static bowling.view.PinView.SPARE;
import static java.util.stream.Collectors.joining;

public class OutputView {

    private static final int TEMPLATE_INTERVAL = 5;

    private static final String PEOPLE_NUMBER_ASK_QST = "How many people?";
    private static final String USERNAME_ASK_QST = "플레이어 %d의 이름은(%d english letters)?:";

    private static final String KNOWN_DOWN_PIN_NUMBER_ASK_QST = "\n %s's turn : ";
    private static final String TEMPLATE_SEPARATOR = "|";
    private static final String NAME_DISPLAY_TEMPLATE = "NAME";

    private static final int SCORE_INTERVAL = 2;

    private static final String roundTemplate = createRoundTemplate();

    private OutputView() {

    }

    public static void printUsernameAskQst(int userIndex) {
        System.out.printf(USERNAME_ASK_QST, userIndex, Username.USERNAME_LENGTH);
    }

    public static void printPinAskQst(Username username) {
        System.out.printf(KNOWN_DOWN_PIN_NUMBER_ASK_QST, username.getName());
    }


    public static void printScore(BowlingGame bowlingGame, List<ScoreResult> scoreResults) {
        System.out.println(roundTemplate);
        getUsernames(bowlingGame)
                .stream()
                .forEach((username) -> printScoreOfUser(bowlingGame, username, scoreResults));
    }

    private static List<Username> getUsernames(BowlingGame bowlingGame) {
        return bowlingGame.getPlayers().getUsernames();
    }

    private static void printScoreOfUser(BowlingGame bowlingGame, Username username, List<ScoreResult> scoreResults) {
        System.out.print(TEMPLATE_SEPARATOR + createTemplateUnit(username.getName()));
        printPin(bowlingGame.findBowlingByUsername(username));
        System.out.print("\n" + TEMPLATE_SEPARATOR + createTemplateUnit(""));
        printScore(scoreResults, username);
        System.out.println();
    }


    private static void printPin(Bowling bowling) {
        IntStream.range(1, BowlingRound.LAST_ROUND_NUM + 1)
                .mapToObj((roundNum) -> getTemplateUnit(bowling, roundNum))
                .forEach(System.out::print);
    }

    private static void printScore(List<ScoreResult> scoreResults, Username username) {
        Integer[] result = cumulativeSum(scoreResults, username);
        IntStream.range(0, BowlingRound.LAST_ROUND_NUM)
                .mapToObj((scoreIndex) -> {
                    if (scoreIndex < result.length) {
                        return createTemplateUnit(result[scoreIndex] + "");
                    }
                    return createTemplateUnit("");
                })
                .forEach(System.out::print);
    }

    private static Integer[] cumulativeSum(List<ScoreResult> scoreResults, Username username) {
        Integer[] result = scoreResults.stream()
                .filter((scoreResult -> scoreResult.isSameUsername(username)))
                .flatMap(scoreResult -> scoreResult.getScores().stream())
                .toArray(Integer[]::new);
        Arrays.parallelPrefix(result, Integer::sum);
        return result;
    }


    private static String getTemplateUnit(Bowling bowling, int roundNum) {
        BowlingRound round = getBowlingRound(bowling, roundNum);
        if (round == null) {
            return createTemplateUnit("");
        }
        Scores scores = round.getScores();
        String view = getView(scores);
        if (scores.isSecondPinSpare()) {
            return createTemplateUnit(replaceToSpareExpression(view, SCORE_INTERVAL));
        }
        if (scores.isThirdPinSpare()) {
            return createTemplateUnit(replaceToSpareExpression(view, SCORE_INTERVAL * 2));
        }
        return createTemplateUnit(view);
    }

    private static BowlingRound getBowlingRound(Bowling bowling, int roundNum) {
        Map<Position, BowlingRound> rounds = bowling.getRounds();
        return rounds.get(new Position(roundNum));
    }

    private static String getView(Scores scores) {
        return scores.getScores().stream()
                .map(PinView::valueOf)
                .collect(joining(AND.getView()));
    }

    private static String replaceToSpareExpression(String result, int replaceTargetIndex) {
        StringBuilder builder = new StringBuilder(result);
        builder.setCharAt(replaceTargetIndex, SPARE.getView().charAt(0));
        return builder.toString();
    }

    private static String createRoundTemplate() {
        return TEMPLATE_SEPARATOR + createTemplateUnit(NAME_DISPLAY_TEMPLATE) +
                IntStream.range(0, BowlingRound.LAST_ROUND_NUM)
                        .mapToObj((roundNum) -> {
                            int viewRoundNum = roundNum + 1;
                            if (viewRoundNum < 10) {
                                return createTemplateUnit("0" + viewRoundNum);
                            }
                            return createTemplateUnit(String.valueOf(viewRoundNum));
                        })
                        .collect(joining());
    }

    private static String createTemplateUnit(String input) {
        int whiteSpaceLength = TEMPLATE_INTERVAL - input.length();
        String whiteSpace = IntStream.range(0, whiteSpaceLength)
                .mapToObj((idx) -> " ")
                .collect(joining());
        return whiteSpace + input + "  " + TEMPLATE_SEPARATOR;
    }


    public static void printConsole(String message) {
        System.out.println(message);
    }

    public static void printPeopleNumberAskQst() {
        System.out.println(PEOPLE_NUMBER_ASK_QST);
    }
}
