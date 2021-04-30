package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NormalFrameTest {

    @Test
    void 테스트() {
        NormalFrame normalFrame = new NormalFrame(1);
        normalFrame.bowl(HitCount.valueOf(8));
        normalFrame.bowl(HitCount.valueOf(2));
        System.out.println(normalFrame.getScore());
    }
}