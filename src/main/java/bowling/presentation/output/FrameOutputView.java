package bowling.presentation.output;

import bowling.domain.Player;
import bowling.domain.frame.Frames;
import bowling.presentation.output.util.FramesOutputHelper;
import bowling.presentation.output.util.ScoresOutputHelper;

import static bowling.presentation.output.constant.FrameSize.INDENT;
import static bowling.presentation.output.constant.ScoreOutput.BOUNDARY;
import static bowling.presentation.output.constant.ScoreOutput.SPACE;

public class FrameOutputView {

    private static final String FIRST_ROW = "| NAME  |  01   |  02   |  03   |  04   |  05   |  06   |  07   |  08   |  09   |  10   |";

    private static final String SECOND_ROW = "       |       |       |       |       |       |       |       |       |       |";

    private static final String THIRD_ROW = "|       |       |       |       |       |       |       |       |       |       |       |";

    private FrameOutputView() {
    }

    public static FrameOutputView create() {
        return new FrameOutputView();
    }

    public void print(Player player) {
        System.out.println(FIRST_ROW);
        System.out.println(name(player) + SECOND_ROW);
        System.out.println(THIRD_ROW);
        System.out.println();
    }

    public void print(Player player, Frames frames) {

        System.out.println(FIRST_ROW);

        StringBuilder secondRow = new StringBuilder();

        secondRow
                .append(name(player))
                .append(FramesOutputHelper.create().output(frames))
                .append(ScoresOutputHelper.create().output(frames));

        System.out.println(secondRow);

    }

    private StringBuilder name(Player player) {
        StringBuilder name = new StringBuilder();
        name.append(BOUNDARY.value()).append(SPACE.value()).append(SPACE.value()).append(player.getName());
        for (int i = player.nameLength(); i < INDENT.value(); i++) {
            name.append(SPACE.value());
        }
        return name.append(BOUNDARY.value());
    }


}
