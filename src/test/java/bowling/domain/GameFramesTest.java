package bowling.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class GameFramesTest {

    GameFrames gameFrames = null;

    @BeforeEach
    public void setup() {
        gameFrames = GameFrames.init();
    }
    @Test
    @DisplayName("입력받은 투구 리스트 사이즈 반환")
    public void 입력받은_투구_리스트_사이즈_반환() {

        NomalFrame nomalFrame = NomalFrame.init();

        nomalFrame.add(Pitch.of(3));
        nomalFrame.add(Pitch.of(4));
        gameFrames.put(1, nomalFrame);

        nomalFrame.add(Pitch.of(6));
        nomalFrame.add(Pitch.of(0));
        gameFrames.put(2, nomalFrame);

        nomalFrame.add(Pitch.of(7));
        nomalFrame.add(Pitch.of(1));
        gameFrames.put(3, nomalFrame);

        assertEquals(gameFrames.size(), 3);
    }

    @Test
    @DisplayName("지정 사이즈 만큼 입력되는지")
    public void 지정_사이즈_정상입력확인() {

        NomalFrame nomalFrame = NomalFrame.init();

        nomalFrame.add(Pitch.of(3));
        nomalFrame.add(Pitch.of(4));
        gameFrames.put(1, nomalFrame);

        nomalFrame.add(Pitch.of(6));
        nomalFrame.add(Pitch.of(0));
        gameFrames.put(2, nomalFrame);

        nomalFrame.add(Pitch.of(7));
        nomalFrame.add(Pitch.of(1));
        gameFrames.put(3, nomalFrame);

        nomalFrame.add(Pitch.of(1));
        nomalFrame.add(Pitch.of(1));
        gameFrames.put(4, nomalFrame);

        nomalFrame.add(Pitch.of(2));
        nomalFrame.add(Pitch.of(1));
        gameFrames.put(5, nomalFrame);

        nomalFrame.add(Pitch.of(7));
        nomalFrame.add(Pitch.of(3));
        gameFrames.put(6, nomalFrame);

        nomalFrame.add(Pitch.of(4));
        nomalFrame.add(Pitch.of(2));
        gameFrames.put(7, nomalFrame);

        nomalFrame.add(Pitch.of(6));
        nomalFrame.add(Pitch.of(2));
        gameFrames.put(8, nomalFrame);

        nomalFrame.add(Pitch.of(1));
        nomalFrame.add(Pitch.of(0));
        gameFrames.put(9, nomalFrame);

        nomalFrame.add(Pitch.of(3));
        nomalFrame.add(Pitch.of(3));
        gameFrames.put(10, nomalFrame);

        assertEquals(gameFrames.size(), 10);
    }
}
