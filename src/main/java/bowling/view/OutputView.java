package bowling.view;

import bowling.domain.frame.*;
import bowling.domain.frame.dto.ScoreBoardDTO;
import bowling.domain.state.*;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class OutputView {

    private static final String CURRENT_FRAME_TXT = "{0} 프레임 투구 : ";
    private static final String HEADER = "|  NAME  |   01   |   02   |   03   |   04   |   05   |   06   |   07   |   08   |   09   |   10   |";
    private static final String SCORE = "|{0}   |{1}   |{2}   |{3}   |{4}   |{5}   |{6}   |{7}   |{8}   |{9}   | {10}  |";
    private static final String TOTAL_SCORE= "|        |{0}   |{1}   |{2}   |{3}   |{4}   |{5}   |{6}   |{7}   |{8}   |{9}   |";
    private static final String EMPTY_STRING = "";
    private static final String STRING_FORMAT = "%5s";
    public static final String SCORE_DELIMITER = "|";

    private OutputView() {
    }

    public static void print(ScoreBoardDTO scoreBoardDTO) {
        System.out.println(HEADER);
        System.out.println(MessageFormat.format(SCORE, toScoreArray(scoreBoardDTO)));
        System.out.println(MessageFormat.format(TOTAL_SCORE, toTotalScoreArray(scoreBoardDTO)));
    }

    private static String[] toScoreArray(ScoreBoardDTO scoreBoardDTO) {
        List<String> result = new ArrayList<>(Arrays.asList(String.format(STRING_FORMAT, scoreBoardDTO.getUser().getName())));
        scoreBoardDTO.getFrames().stream()
                .map(OutputView::makeGameResult)
                .map(gameResult -> String.format(STRING_FORMAT, gameResult))
                .collect(Collectors.toCollection(() -> result));

        generateEmptyFrame(result);
        return result.stream().toArray(String[]::new);
    }

    private static void generateEmptyFrame(List<String> result) {
        for (int size = result.size(); size <= Index.MAX_INDEX; size++) {
            result.add(String.format(STRING_FORMAT, EMPTY_STRING));
        }
    }

    public static void printCurrentFrame(Index size) {
        System.out.println();
        System.out.print(MessageFormat.format(CURRENT_FRAME_TXT, size.getIndex()));
    }

    private static String[] toTotalScoreArray(ScoreBoardDTO scoreBoardDTO) {
        List<Integer> result = scoreBoardDTO.getFrames().stream()
                .map(frame -> frame.getScore())
                .filter(score -> !score.isPending())
                .map(Score::getScore)
                .collect(toList());

        return collectedTotalScore(result).stream()
                .toArray(String[]::new);
    }

    private static List<String> collectedTotalScore(List<Integer> list) {
        List<String> result = new ArrayList<>();
        int current = 0;
        for (Integer score : list) {
            current += score;
            result.add(String.format(STRING_FORMAT, current));
        }
        generateEmptyFrame(result);
        return result;
    }

    public static String makeGameResult(Frame frame) {
        if(frame instanceof EndFrame) {
            return ((EndFrame) frame).getStates().stream()
                    .filter(state -> !(state instanceof Ready))
                    .map(OutputView::makeSymbol)
                    .collect(Collectors.joining(SCORE_DELIMITER));
        }

        return makeSymbol(((NormalFrame) frame).getState());
    }

    private static String makeSymbol(State state) {

        String stateSymbol = null;
        Symbol symbol = Symbol.valueOf(state.getClass().getSimpleName());

        switch (symbol) {
            case Strike:
                stateSymbol = Symbol.Strike.toString();
                break;
            case Spare:
                stateSymbol = Symbol.getSpareSymbol(state);
                break;
            case Miss:
                stateSymbol = Symbol.getMissSymbol(state);
                break;
            case Gutter:
                stateSymbol = Symbol.AllGutter.toString();
                break;
            case Ready:
                stateSymbol = Symbol.Ready.toString();
                break;
            case Continue:
                stateSymbol = Symbol.getContinueSymbol(state);
                break;
        }
        return stateSymbol;
    }
}
