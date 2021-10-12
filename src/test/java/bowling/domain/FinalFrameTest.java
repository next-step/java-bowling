package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class FinalFrameTest {


    @Test
    @DisplayName("마지막 프레임 생성 테스트")
    void create(){
        FinalFrame finalFrame = new FinalFrame();

        assertThat(finalFrame.status()).isEqualTo(FrameStatus.READY);
    }
}