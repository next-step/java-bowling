package bowling.presentation.output;

import bowling.domain.Player;
import bowling.domain.Players;
import bowling.presentation.output.util.FramesOutputHelper;
import bowling.presentation.output.util.TotalScoreOutputHelper;

import static bowling.presentation.output.constant.FrameSize.INDENT;
import static bowling.presentation.output.constant.ScoreMarking.BOUNDARY;
import static bowling.presentation.output.constant.ScoreMarking.SPACE;

public class PlayersFrameOutputView {

    private static final String FIRST_ROW = "| NAME  |  01   |  02   |  03   |  04   |  05   |  06   |  07   |  08   |  09   |  10   |";

    private final Players players;

    private PlayersFrameOutputView(Players players) {
        this.players = players;
    }

    public static PlayersFrameOutputView create(Players players) {
        return new PlayersFrameOutputView(players);
    }

    public void print() {
        players.all().forEach(player -> {
            System.out.println(FIRST_ROW);
            StringBuilder secondRow = new StringBuilder();

            secondRow
                    .append(name(player))
                    .append(FramesOutputHelper.create().output(player.getFrames()))
                    .append(TotalScoreOutputHelper.create().output(player.getFrames()));
            System.out.println(secondRow);
        });
        System.out.println();
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
