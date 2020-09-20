package bowling.view;

import bowling.domain.frame.EndFrame;
import bowling.domain.frame.Frame;
import bowling.domain.frame.NormalFrame;
import bowling.domain.frame.ScoreBoard;
import bowling.domain.state.*;
import bowling.domain.user.dto.UserDTO;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class OutputView {

    private static final String CURRENT_FRAME_TXT = "{0} 프레임 투구 : ";
    private static final String HEADER = "|  NAME  |   01   |   02   |   03   |   04   |   05   |   06   |   07   |   08   |   09   |   10   |";
    private static final String SCORE = "|{0}   |{1}   |{2}   |{3}   |{4}   |{5}   |{6}   |{7}   |{8}   |{9}   | {10}  |";
    private static final String EMPTY_STRING = "";
    private static final String STRING_FORMAT = "%5s";

    private OutputView() {
    }

    public static void print(UserDTO userDTO, ScoreBoard scoreBoard) {
        System.out.println(HEADER);
        String format = MessageFormat.format(SCORE, toScoreArray(userDTO, scoreBoard));
        System.out.println(format);
    }

    private static String[] toScoreArray(UserDTO userDTO, ScoreBoard scoreBoard) {
        List<String> result = new ArrayList<>(Arrays.asList(String.format(STRING_FORMAT, userDTO.getName())));
        scoreBoard.getFrames().stream()
                .map(frame -> makeGameResult(frame))
                .map(gameResult -> String.format(STRING_FORMAT, gameResult))
                .collect(Collectors.toCollection(() -> result));

        generateEmptyFrame(result);
        return result.stream().toArray(String[]::new);
    }

    private static void generateEmptyFrame(List<String> result) {
        for (int size = result.size(); size <= ScoreBoard.END_FRAME_COUNT; size++) {
            result.add(String.format(STRING_FORMAT, EMPTY_STRING));
        }
    }

    public static void printCurrentFrame(int size) {
        System.out.println();
        System.out.print(MessageFormat.format(CURRENT_FRAME_TXT, size));
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
        Symbol symbol = Symbol.valueOf(state.getClass().getSimpleName());

        switch (symbol) {
            case Strike:
                stateSymbol = Symbol.Strike.toString();
                break;
            case Spare:
                String spareFirst = ((Spare) state).getFirstPin().toString();
                if(checkGutter(spareFirst))
                    spareFirst = Symbol.Gutter.toString();
                stateSymbol = spareFirst + Symbol.Spare.toString();
                break;
            case Miss:
                String missFirst = ((Miss) state).getFirstPin().toString();
                String missSecond = ((Miss) state).getSecondPin().toString();

                if(checkGutter(missFirst))
                    missFirst = Symbol.Gutter.toString();
                if(checkGutter(missSecond))
                    missSecond = Symbol.Gutter.toString();

                stateSymbol = missFirst + Symbol.Miss.toString() + missSecond;
                break;
            case Gutter:
                stateSymbol = Symbol.AllGutter.toString();
                break;
            case Ready:
                stateSymbol = Symbol.Ready.toString();
                break;
            case Continue:
                String continueFirst = ((Continue) state).getFirstPin().toString();

                if(checkGutter(continueFirst))
                    continueFirst = Symbol.Gutter.toString();

                stateSymbol = continueFirst;
                break;
        }
        return stateSymbol;
    }

    private static boolean checkGutter(String felledPin) {
        return Integer.parseInt(felledPin) == 0 ? true : false;
    }
}
