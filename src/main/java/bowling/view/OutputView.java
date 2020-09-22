package bowling.view;

import bowling.domain.frame.*;
import bowling.domain.frame.dto.ScoreBoardDTO;
import bowling.domain.game.dto.BowlingGameDTO;
import bowling.domain.state.*;
import bowling.domain.user.dto.UserDTO;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class OutputView {

    private static final String CURRENT_USER_TXT = "{0}\''s turn: ";
    private static final String HEADER = "|  NAME  |   01   |   02   |   03   |   04   |   05   |   06   |   07   |   08   |   09   |   10   |";
    private static final String SCORE = "|{0}   |{1}   |{2}   |{3}   |{4}   |{5}   |{6}   |{7}   |{8}   |{9}   | {10}  |";
    private static final String TOTAL_SCORE= "|        |{0}   |{1}   |{2}   |{3}   |{4}   |{5}   |{6}   |{7}   |{8}   |{9}   |";
    private static final String EMPTY_STRING = "";
    private static final String STRING_FORMAT = "%5s";

    private OutputView() {
    }

    public static void print(BowlingGameDTO bowlingGameDTO) {
        System.out.println(HEADER);
        bowlingGameDTO.getScoreBoardDTOs().forEach(OutputView::printScoreBoard);
    }

    public static void printScoreBoard(ScoreBoardDTO scoreBoardDTO) {
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

    public static void printCurrentUser(UserDTO userDTO) {
        System.out.print(MessageFormat.format(CURRENT_USER_TXT, userDTO.getName()));
    }

    private static String[] toTotalScoreArray(ScoreBoardDTO scoreBoardDTO) {
        List<Integer> result = scoreBoardDTO.getFrames().stream()
                .map(frame -> frame.getScore())
                .filter(score -> !score.isPending())
                .map(score -> score.getScore())
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

    public static String makeTotalScoreResult(Frame frame) {
        if(frame instanceof EndFrame) {
            return ((EndFrame) frame).getStates().stream()
                    .filter(state -> !(state instanceof Ready))
                    .map(endState -> makeSymbol(endState))
                    .collect(Collectors.joining("|"));
        }

        return makeSymbol(((NormalFrame) frame).getState());
    }

    public static String makeGameResult(Frame frame) {
        if(frame instanceof EndFrame) {
            return ((EndFrame) frame).getStates().stream()
                    .filter(state -> !(state instanceof Ready))
                    .map(endState -> makeSymbol(endState))
                    .collect(Collectors.joining("|"));
        }
        return makeSymbol(((NormalFrame) frame).getState());
    }

    private static String makeSymbol(State state) {

        String stateSymbol = null;
        Symbol symbol = Symbol.valueOf(state.getClass().getSimpleName().toUpperCase());

        switch (symbol) {
            case STRIKE:
                stateSymbol = Symbol.STRIKE.getSymbol(state);
                break;
            case SPARE:
                stateSymbol = Symbol.SPARE.getSymbol(state);
                break;
            case MISS:
                stateSymbol = Symbol.MISS.getSymbol(state);
                break;
            case GUTTER:
                stateSymbol = Symbol.ALL_GUTTER.getSymbol(state);
                break;
            case READY:
                stateSymbol = Symbol.READY.getSymbol(state);
                break;
            case CONTINUE:
                stateSymbol = Symbol.CONTINUE.getSymbol(state);
                break;
        }
        return stateSymbol;
    }
}
