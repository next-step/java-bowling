package bowling.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FramesTest {

    private Frames frames;

    @BeforeEach
    void setup(){
        frames = new Frames();
    }

    @DisplayName("프레임들 하나씩 추가한다.")
    @Test
    void addFrame(){
        frames.first(10);
    }

}