package step2.view;

import step2.domain.Player;

import java.util.List;

public interface ResultView {

    void drawHeaderFrame(int frameNo, int pitchesCount);

    void drawFrame(Player player, List<String> marks);

    void drawEmptyLine(String name);
}
