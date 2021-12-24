package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class NormalFrameTest {

    @Test
    @DisplayName("노멀프레임 객체 생성 - 투구 개수, 리스트에 맞춘 핀 개수를 담는 리스트 생성")
    void create() {
        NormalFrame normalFrame = new NormalFrame();
        assertThat(normalFrame).isEqualTo(new NormalFrame());
    }

    @Test
    @DisplayName("두번째 히팅 핀이 들어왔을 경우에, 두개 합이 11이상이면 IllegalArgumentException 반환")
    void invalid() {
        NormalFrame normalFrame = new NormalFrame();
        normalFrame.addKnockDownPins(7);
        assertThatThrownBy(() -> normalFrame.addKnockDownPins(4))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("두번째 히팅 핀이 들어왔을 경우에, 두개 합이 10이하이면 정상")
    void valid() {
        NormalFrame normalFrame = new NormalFrame();
        normalFrame.addKnockDownPins(7);
        assertThatCode(() -> normalFrame.addKnockDownPins(3))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("맞춘 핀을 리스트에 담음 - 스트라이크가 아닐 경우")
    void addKnockDownPinsNoStrike() {
        NormalFrame normalFrame = new NormalFrame();
        normalFrame.addKnockDownPins(3);
        assertThat(normalFrame.getBowlingPins()).size().isEqualTo(1);
        assertThat(normalFrame.getBowlingPins().get(0)).isEqualTo(new BowlingPins(3));
        assertThat(normalFrame.getCountOfPitching()).isEqualTo(2);
    }

    @Test
    @DisplayName("맞춘 핀을 리스트에 다음 - 스트라이크 일 경우 - 다음 투구 불가능하도록 변경")
    void addBowlingPinsStrike() {
        NormalFrame normalFrame = new NormalFrame();
        normalFrame.addKnockDownPins(10);
        assertThat(normalFrame.getBowlingPins()).size().isEqualTo(1);
        assertThat(normalFrame.getBowlingPins().get(0)).isEqualTo(new BowlingPins(10));
        assertThat(normalFrame.getCountOfPitching()).isEqualTo(1);
    }

    @Test
    @DisplayName("투구가 가능한지 테스트")
    void isPossiblePitching() {
        NormalFrame normalFrame = new NormalFrame();
        normalFrame.addKnockDownPins(5);
        assertThat(normalFrame.isPossiblePitching()).isTrue();
    }

    @Test
    @DisplayName("투구 불가능한지 테스트 - 스트라이크일 경우 false")
    void isImpossiblePitching() {
        NormalFrame normalFrame = new NormalFrame();
        normalFrame.addKnockDownPins(10);
        assertThat(normalFrame.isPossiblePitching()).isFalse();
    }

    @Test
    @DisplayName("투구 불가능한지 테스트 - 2번 던졌을 경우 false")
    void isImpossiblePitching2() {
        NormalFrame normalFrame = new NormalFrame();
        normalFrame.addKnockDownPins(8);
        normalFrame.addKnockDownPins(1);
        assertThat(normalFrame.isPossiblePitching()).isFalse();
    }


}