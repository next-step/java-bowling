package step3.view;

import step3.domain.GameHistory;
import step3.domain.Player;

import java.util.List;

public interface ResultView {

    void drawHeaderFrame(int frameNo, int pitchesCount);

    void drawFrame(Player player, GameHistory gameHistory);

    void drawEmptyLine(String name);
}
