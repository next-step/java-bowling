package bowling.view;

import bowling.domain.Frame;
import bowling.domain.Frames;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ResultView {

    private final String FRAME_PREFIX_SEPERATOR = "| ";
    private final String FRAME_SURFIX_SEPERATOR = "   |";
    private final String FRAME_SEPERATOR = " | ";

    private final String FRAME_RESULT_BOARD_TEMPLATE = "| %4s |  %-3s |  %-3s |  %-3s |  %-3s |  %-3s |  %-3s |  %-3s |  %-3s |  %-3s |  %-5s |\n";


    public void showScoreBoard(Frames frames) {
        System.out.println(makeScoreBoardHeader());

        List<String> frameBody = new ArrayList<>();
        frameBody.add(frames.getPlayerName());
        frameBody.addAll(frames.getFrames().stream().map(frame -> frame.desc()).collect(Collectors.toList()));

        blankFrame(frames, frameBody);

        System.out.printf(FRAME_RESULT_BOARD_TEMPLATE, frameBody.toArray());
    }

    private void blankFrame(Frames frames, List<String> frameBody) {
        int size = frames.size();
        for (int i = 0; i < 10 - size; i++) {
            frameBody.add("");
        }
    }

    private String makeScoreBoardHeader() {
        return Stream.of("NAME", " 01 ", " 02 ", " 03 ", " 04 ", " 05 ", " 06 ", " 07 ", " 08 ", " 09 ", " 10 ")
                .collect(Collectors.joining(FRAME_SEPERATOR, FRAME_PREFIX_SEPERATOR, FRAME_SURFIX_SEPERATOR));
    }
}
