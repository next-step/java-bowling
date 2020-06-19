package bowling.domain;

import bowling.domain.frame.FinalFrame;
import bowling.domain.frame.Frame;
import bowling.domain.frame.NormalFrame;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

class NormalFrameTest {

    @DisplayName("NormalFrame 객체 정상 생성(첫 번째)")
    @Test
    public void initiateNormalFrame_정상() {
        assertThatCode(() -> {
            NormalFrame.initiate();
        }).doesNotThrowAnyException();
    }

    @DisplayName("다음 NormalFrame 객체 생성 : 첫 번째 투구에서 스트라이크를 쳤을 때")
    @Test
    public void nextNormalFrame_스트라이크() {
        Frame normalFrame = NormalFrame.initiate();
        normalFrame.bowl(10);

        assertThat(normalFrame.getIndex()).isEqualTo(1);

        Frame nextFrame = normalFrame.next();

        assertThat(nextFrame.getIndex()).isEqualTo(2);
    }

    @DisplayName("다음 NormalFrame 객체 생성 : 투구를 두 번 완료 했을 때")
    @Test
    public void nextNormalFrame_두번_투구() {
        Frame normalFrame = NormalFrame.initiate();
        normalFrame.bowl(3);
        normalFrame.bowl(4);

        assertThat(normalFrame.getIndex()).isEqualTo(1);

        Frame nextFrame = normalFrame.next();

        assertThat(nextFrame.getIndex()).isEqualTo(2);
    }

    @DisplayName("다음 NormalFrame 객체 생성 : 조건이 충족되지 않으면 현재 Frame을 반환함")
    @Test
    public void nextNormalFrame_그외() {
        Frame normalFrame = NormalFrame.initiate();
        normalFrame.bowl(3);

        assertThat(normalFrame.getIndex()).isEqualTo(1);

        Frame nextFrame = normalFrame.next();

        assertThat(nextFrame.getIndex()).isEqualTo(1);
        assertThat(normalFrame).isEqualTo(nextFrame);
    }

    @DisplayName("10번째 인덱스에 해당하는 프레임을 생성할 시 FinalFrame을 생성함")
    @Test
    public void nextFrame_Final() {
        Frame frame = NormalFrame.initiate();
        for (int i = 0; i < 9; i++) {
            frame.bowl(10);
            frame = frame.next();
        }

        assertThat(frame.getClass()).isEqualTo(FinalFrame.class);
    }
}
