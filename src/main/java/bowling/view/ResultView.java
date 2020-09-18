package bowling.view;

import bowling.domain.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ResultView {

    private static final String FRAME_PREFIX_SEPERATOR = "| ";
    private static final String FRAME_SURFIX_SEPERATOR = "   |";
    private static final String FRAME_SEPERATOR = " | ";

    private static final String FRAME_RESULT_BOARD_TEMPLATE = "| %4s |  %-3s |  %-3s |  %-3s |  %-3s |  %-3s |  %-3s |  %-3s |  %-3s |  %-3s |  %-5s |\n";


    public void showScoreBoard(Player player) {
        System.out.println(makeScoreBoardHeader());

        List<String> frameBody = new ArrayList<>();
        frameBody.add(player.name());
        frameBody.addAll(player.frames().stream()
                .map(frame -> frame.currentFrameStatus())
                .collect(Collectors.toList()));

        blankFrame(player.frameSize(), frameBody);

        System.out.printf(FRAME_RESULT_BOARD_TEMPLATE, frameBody.toArray());
    }

    private void blankFrame(int frameSize, List<String> frameBody) {
        for (int i = 0; i < 10 - frameSize; i++) {
            frameBody.add("");
        }
    }

    private String makeScoreBoardHeader() {
        return Stream.of("NAME", " 01 ", " 02 ", " 03 ", " 04 ", " 05 ", " 06 ", " 07 ", " 08 ", " 09 ", " 10 ")
                .collect(Collectors.joining(FRAME_SEPERATOR, FRAME_PREFIX_SEPERATOR, FRAME_SURFIX_SEPERATOR));
    }
}
