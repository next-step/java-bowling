package bowling.presentation.output;

import bowling.domain.Player;
import bowling.domain.frame.Frames;
import bowling.presentation.output.util.FramesOutputHelper;

public class FrameOutputView {

    private static final String FIRST_ROW = "| NAME  |  01   |  02   |  03   |  04   |  05   |  06   |  07   |  08   |  09   |  10   |";

    private static final String BOUNDARY = "|";

    private static final String SPACE = " ";

    private static final int INDENT_SIZE = 5;


    private FrameOutputView() {
    }

    public static FrameOutputView create() {
        return new FrameOutputView();
    }

    public void print(Player player) {
        System.out.println(FIRST_ROW);
        System.out.println(name(player) + "       |       |       |       |       |       |       |       |       |       |");
    }

    public void print(Player player, Frames frames) {

        System.out.println(FIRST_ROW);

        StringBuilder secondRow = new StringBuilder();

        secondRow
                .append(name(player))
                .append(FramesOutputHelper.create().output(frames));

        System.out.println(secondRow);

    }

    private StringBuilder name(Player player) {
        StringBuilder name = new StringBuilder();
        name.append(BOUNDARY).append(SPACE).append(SPACE).append(player.getName());
        for (int i = player.nameLength(); i < INDENT_SIZE; i++) {
            name.append(SPACE);
        }
        return name.append(BOUNDARY);
    }


}
