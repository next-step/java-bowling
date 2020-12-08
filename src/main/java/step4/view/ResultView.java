package step4.view;

import step4.domain.GameHistory;
import step4.domain.Player;

public interface ResultView {

    void drawHeaderFrame(int frameNo, int pitchesCount);

    void drawFrame(Player player, GameHistory gameHistory);

    void drawEmptyLine(String name);
}
