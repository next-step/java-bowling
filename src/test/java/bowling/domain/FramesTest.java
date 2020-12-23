package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.assertThat;

public class FramesTest {

    private static final int MAX_FRAME_COUNT = 10;

    @DisplayName("10개의 프레임 생성 및 모든 투구 완료 시 isEnd Test")
    @Test
    void isEndTest(){

        // given
        Frames frames = Frames.of();

        // when
        for (int i=0; i<MAX_FRAME_COUNT * 2; i++) {
            frames.execute(2);
            frames.makeNextFrames();
        }

        // then
        assertThat(frames.isEnd()).isTrue();
    }


    @DisplayName("한 프레임의 투구 기회가 끝나지 않은 경우, 다음 프레임 생성 안됨.")
    @Test
    void makeNextFrameWhenCurrentIsNotEnd(){
        // given
        Frames frames = Frames.of();
        frames.execute(2);
        int currentFrameSize = frames.getFrames().size();

        // when
        frames.makeNextFrames();

        // then
        assertThat(currentFrameSize).isEqualTo(frames.getFrames().size());
    }
}
