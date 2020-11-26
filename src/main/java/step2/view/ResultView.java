package step2.view;

import step2.domain.GameHistories;
import step2.domain.dto.PlayerDTO;

public interface ResultView {

    void drawHeaderFrame(int frameNo, int pitchesCount);

    void drawFrames(PlayerDTO playerDTO);

    void drawFrames(GameHistories histories);

    void drawEmptyLine(String name);
}
