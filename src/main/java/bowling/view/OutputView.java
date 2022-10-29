package bowling.view;

import bowling.domain.*;

import java.util.Map;
import java.util.stream.IntStream;

import static bowling.view.PinView.AND;
import static bowling.view.PinView.SPARE;
import static java.util.stream.Collectors.joining;

public class OutputView {

    private static final int TEMPLATE_INTERVAL = 5;

    private static final String USERNAME_ASK_QST = "플레이어 이름은(%d english letters)?:";

    private static final String KNOWN_DOWN_PIN_NUMBER_ASK_QST = "\n %d프레임 투구 : ";
    private static final String TEMPLATE_SEPARATOR = "|";
    private static final String NAME_DISPLAY_TEMPLATE = "NAME";

    private static final int SCORE_INTERVAL = 2;

    private static final String roundTemplate = createRoundTemplate();

    private OutputView() {

    }

    public static void printUsernameAskQst() {
        System.out.printf(USERNAME_ASK_QST, Username.USERNAME_LENGTH);
    }

    public static void printPinAskQst(BowlingRound round) {
        System.out.printf(KNOWN_DOWN_PIN_NUMBER_ASK_QST, round.getRoundNumber());
    }


    public static void printScore(Bowling bowling, Username username) {
        System.out.println(roundTemplate);
        System.out.print(TEMPLATE_SEPARATOR + createTemplateUnit(username.getName()));
        IntStream.range(1, BowlingRound.LAST_ROUND_NUM + 1)
                .mapToObj((roundNum) -> getTemplateUnit(bowling, roundNum))
                .forEach(System.out::print);
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
}
