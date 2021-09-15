package bowling.presentation.output;

import bowling.domain.Player;
import bowling.domain.frame.FinalFrame;
import bowling.domain.frame.NormalFrames;
import bowling.presentation.output.util.FinalFrameOutputViewHelper;
import bowling.presentation.output.util.NormalFrameOutputViewHelper;

public class FrameOutputView {

    private static final String FIRST_ROW = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";

    private static final String BOUNDARY = "|";

    private static final String SPACE = " ";

    private static final String EMPTY_FRAME = "|      ";

    private static final int NORMAL_INDENT_SIZE = 4;


    private FrameOutputView() {
    }

    public static FrameOutputView create() {
        return new FrameOutputView();
    }

    public void print(Player player) {
        print(player, NormalFrames.empty());
    }

    public void print(Player player, NormalFrames normalFrames) {

        System.out.println(FIRST_ROW);

        StringBuilder secondRow = new StringBuilder();

        secondRow
                .append(name(player))
                .append(NormalFrameOutputViewHelper.create().output(normalFrames))
                .append(EMPTY_FRAME)
                .append(BOUNDARY);

        System.out.println(secondRow);

    }


    public void print(Player player, NormalFrames normalFrames, FinalFrame finalFrame) {

        System.out.println(FIRST_ROW);

        StringBuilder secondRow = new StringBuilder();

        secondRow
                .append(name(player))
                .append(NormalFrameOutputViewHelper.create().output(normalFrames))
                .append(FinalFrameOutputViewHelper.create().output(finalFrame))
                .append(BOUNDARY);

        System.out.println(secondRow);

    }

    private StringBuilder name(Player player) {
        StringBuilder name = new StringBuilder();
        name.append(BOUNDARY).append(SPACE).append(SPACE).append(player.getName());
        for (int i = player.nameLength(); i < NORMAL_INDENT_SIZE; i++) {
            name.append(SPACE);
        }
        return name;
    }


}
