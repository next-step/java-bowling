package bowling.view;

import bowling.domain.Frame;
import bowling.domain.Frames;

import java.util.stream.Collectors;

public class ResultView {
    private static final String FRAME_HEAD = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";

    public static void viewRetry(Exception e) {
        System.out.println(e.getMessage());
    }

    public static void viewResult(Frames frames) {
        viewFrameHead();
        viewFrames(frames);
    }

    private static void viewFrames(Frames frames) {
        viewPlayerName(frames.getPlayerName());
        String frameViews = frames.getFrames().stream()
                .map(frame -> viewFrame(frame))
                .collect(Collectors.joining(" | "));
        System.out.println(frameViews);
    }

    private static String viewFrame(Frame frame) {
        String framePoints = frame.getPoints().stream()
                .map(point -> String.valueOf(point.getPoint()))
                .collect(Collectors.joining(" "));
        return String.format("%5s", framePoints);
//        System.out.print(String.format("%5s", framePoints));
    }

    private static void viewPlayerName(String name) {
        System.out.print(String.format("|  %s |", name));
    }

    private static void viewFrameHead() {
        System.out.println(FRAME_HEAD);
    }
}
