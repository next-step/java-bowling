package step4.view;

import step4.domain.GameHistories;

import java.util.List;

public interface ResultView {

    void drawHeaderFrame(int frameNo, int pitchesCount);

    void drawFrame(GameHistories histories);

    void drawEmptyLine(List<String> names);
}
