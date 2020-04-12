package bowling.view;

import bowling.domain.Player;

public class OutputView {
    private static final String FRAME_SECTION = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
    private static final String SCORE_SECTION = "|%6s|%6s|%6s|%6s|%6s|%6s|%6s|%6s|%6s|%6s|%6s|\n";

    public void draw(Player player, int numberOfPin) {
        drawTitle();
        drawResult(player, numberOfPin);
    }

    private void drawTitle() {
        System.out.println(FRAME_SECTION);
    }

    private void drawResult(Player player, int numberOfPin) {
        System.out.println(player);

        System.out.println(player.getFrames());

        System.out.printf(SCORE_SECTION,"kkk","","","","","","","","","","");
    }
}
