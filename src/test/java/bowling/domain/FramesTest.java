package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FramesTest {

    @DisplayName("Frames 생성")
    @Test
    void createFrames() {
        Frames frames = new Frames();
        assertEquals(frames.getRound(), 1);
    }

    @DisplayName("strike swing은 바로 다음 라운드 시작")
    @Test
    void strikeSwingCase() {
        Frames frames = new Frames();
        frames.swing(10);
        assertEquals(frames.getRound(), 2);
    }

    @DisplayName("게임 종료 테스트")
    @Test
    void fullOfFramesTest() {
        Frames frames = new Frames();

        // 1 ~ 9프레임
        for (int i = 1; i <= 9; i++) {
            frames.swing(10);
        }

        // 10프레임
        frames.swing(10);
        frames.swing(10);
        frames.swing(10);

        assertTrue(frames.isEnd());
    }

    @DisplayName("getSwingHistory 값 테스트")
    @Test
    void swingHistoryValueTest() {

        Frames frames = new Frames();

        List<String> swingHistory = frames.getSwingHistory();
        for (String history : swingHistory) {
            assertTrue(history.isEmpty());
        }

        frames.swing(10);
        swingHistory = frames.getSwingHistory();
        assertEquals(swingHistory.get(0), "X");
        for (int i = 1; i < 10; i++) {
            assertTrue(swingHistory.get(i).isEmpty());
        }
    }
}
