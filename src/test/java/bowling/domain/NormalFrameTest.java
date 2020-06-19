package bowling.domain;

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
        NormalFrame normalFrame = NormalFrame.initiate();
        normalFrame.bowl(10);

        assertThat(normalFrame.getIndex()).isEqualTo(1);

        NormalFrame nextFrame = normalFrame.next();

        assertThat(nextFrame.getIndex()).isEqualTo(2);
    }

    @DisplayName("다음 NormalFrame 객체 생성 : 투구를 두 번 완료 했을 때")
    @Test
    public void nextNormalFrame_두번_투구() {
        NormalFrame normalFrame = NormalFrame.initiate();
        normalFrame.bowl(3);
        normalFrame.bowl(4);

        assertThat(normalFrame.getIndex()).isEqualTo(1);

        NormalFrame nextFrame = normalFrame.next();

        assertThat(nextFrame.getIndex()).isEqualTo(2);
    }

    @DisplayName("다음 NormalFrame 객체 생성 : 조건이 충족되지 않으면 현재 Frame을 반환함")
    @Test
    public void nextNormalFrame_그외() {
        NormalFrame normalFrame = NormalFrame.initiate();
        normalFrame.bowl(3);

        assertThat(normalFrame.getIndex()).isEqualTo(1);

        NormalFrame nextFrame = normalFrame.next();

        assertThat(nextFrame.getIndex()).isEqualTo(1);
        assertThat(normalFrame).isEqualTo(nextFrame);
    }

    @DisplayName("1번 투구(Bowling) 하면 인스턴스 변수 PitchesGroup에 기록함")
    @Test
    public void bowling_기록() { //테스트 변경 필요
        NormalFrame normalFrame = NormalFrame.initiate();

        normalFrame.bowl(4);
    }
}
