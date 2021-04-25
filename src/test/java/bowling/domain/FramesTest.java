package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.BDDAssertions.then;

class FramesTest {

    @Test
    @DisplayName("프레임 인덱스를 인자로 받아 해당 프레임을 업데이트한다.")
    public void update() throws Exception {
        //given
        Frames frames = new Frames();
        NormalPinCounts normalPinCounts = new NormalPinCounts();
        normalPinCounts.knockDown(5);
        frames.update(new NormalFrame(1, normalPinCounts));

        //when
        Frame frame = frames.get(1);
        int index = frame.index();

        then(frame.pinCounts().pinCounts().get(0)).isEqualTo(new PinCount(5));
        then(index).isEqualTo(1);
    }
}