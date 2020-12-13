package bowling.view;

import bowling.entity.*;

import java.util.Optional;

public class CurrentScoreBoardView extends BaseView {

    private static final String BAR = "|";
    private static final String SPACE = " ";
    private static final String EMPTY_CELL = "|       ";

    public static void display(User user, Frames frames) {
        System.out.println("점수판");

        printHeader(frames.getTotalFrameCount());
        printScoreStatus(user, frames);
    }

    private static void printHeader(int frameCount) {
        append(userNameCell("NAME"));
        for (int frameNumber = 1; frameNumber <= frameCount; frameNumber++) {
            append(frameHeaderCell(frameNumber));
        }
        append(BAR);
        printAndClear(PrintType.NEW_LINE);
    }

    private static String userNameCell(String value) {
        return String.format("| %4s ", value);
    }

    private static String frameHeaderCell(int frameNumber) {
        return String.format("|  %02d  ", frameNumber);
    }

    private static void printScoreStatus(User user, Frames frames) {
        append(userNameCell(user.getName()));
        for (int frameNumber = 1; frameNumber <= frames.getTotalFrameCount(); frameNumber++) {
            String frameCell = frames.getFrame(frameNumber)
                    .map(CurrentScoreBoardView::frameCell)
                    .orElse(EMPTY_CELL);
            append(frameCell);
        }
        append(BAR);
        printAndClear(PrintType.NEW_LINE);
    }

    private static String frameCell(Frame frame) {

        FrameResult frameResult = frame.getFrameResult();
        if (frameResult == FrameResult.STRIKE) {
            return String.format("|  X   ");
        }

        Pitch pitch = frame.getPitch();
        String firstScore = pitch.getFirstScore()
                .map(Score::toString)
                .map(CurrentScoreBoardView::gutter)
                .orElse(SPACE);
        String secondScore = pitch.getSecondScore()
                .map(Score::toString)
                .map(CurrentScoreBoardView::gutter)
                .orElse(SPACE);

        if (frameResult == FrameResult.NOT_FINISHED) {
            return String.format("|  %s   ", firstScore);
        }

        if (frameResult == FrameResult.SPARE) {
            return String.format("|  %s|/ ", firstScore);
        }

        return String.format("|  %s|%s ", firstScore, secondScore);
    }

    private static String gutter(String score) {
        if ("0".equals(score)) {
            return "-";
        }
        return score;
    }
}
