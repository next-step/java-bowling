package step3.view;

import step3.domain.Player;

import java.util.List;

public interface ResultView {

    void drawHeaderFrame(int frameNo, int pitchesCount);

    void drawFrame(Player player, List<String> marks);

    void drawEmptyLine(String name);
}
