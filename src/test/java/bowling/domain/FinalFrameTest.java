package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class FinalFrameTest {

    @Test
    @DisplayName("파이널프레임 객체 생성 - 투구 개수, 리스트에 맞춘 핀 개수를 담는 리스트 생성")
    void create() {
        FinalFrame finalFrame = new FinalFrame();
        assertThat(finalFrame).isEqualTo(new FinalFrame());
    }

    @Test
    @DisplayName("두번째 히팅 핀이 들어왔을 경우에, 두개 합이 11이상이면 IllegalArgumentException 반환")
    void invalidSecond() {
        FinalFrame finalFrame = new FinalFrame();
        finalFrame.addKnockDownPins(7);
        assertThatThrownBy(() -> finalFrame.addKnockDownPins(4))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("세번째 히팅 핀이 들어왔을 경우에, 두개 합이 11이상이면 IllegalArgumentException 반환")
    void invalidThird() {
        FinalFrame finalFrame = new FinalFrame();
        finalFrame.addKnockDownPins(10);
        finalFrame.addKnockDownPins(7);
        assertThatThrownBy(() -> finalFrame.addKnockDownPins(4))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("세번째 히팅 핀이 들어왔을 경우에, 두개 합이 10이하이면 정상")
    void valid() {
        FinalFrame finalFrame = new FinalFrame();
        finalFrame.addKnockDownPins(10);
        finalFrame.addKnockDownPins(10);
        assertThatCode(() -> finalFrame.addKnockDownPins(10))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("맞춘 핀을 리스트에 다음 - 1투구(8), 2투구(1), 3투구(보너스 없음)")
    void addBowlingPins() {
        FinalFrame finalFrame = new FinalFrame();
        finalFrame.addKnockDownPins(8);
        finalFrame.addKnockDownPins(1);
        assertThat(finalFrame.isPossiblePitching()).isFalse();
        assertThat(finalFrame.getBowlingPins()).size().isEqualTo(2);
        assertThat(finalFrame.getCountOfPitching()).isEqualTo(2);
    }

    @Test
    @DisplayName("맞춘 핀을 리스트에 다음 - 1투구(5), 2투구(스페어), 3투구(보너스 있음)")
    void addBowlingPinsSpare() {
        FinalFrame finalFrame = new FinalFrame();
        finalFrame.addKnockDownPins(5);
        finalFrame.addKnockDownPins(5);
        assertThat(finalFrame.isPossiblePitching()).isTrue();
        assertThat(finalFrame.getBowlingPins()).size().isEqualTo(2);
        assertThat(finalFrame.getCountOfPitching()).isEqualTo(3);
    }

    @Test
    @DisplayName("맞춘 핀을 리스트에 다음 - 1투구(스트라이크), 2투구(스트라이크), 3투구(보너스있음)")
    void addBowlingPinsStrike() {
        FinalFrame finalFrame = new FinalFrame();
        finalFrame.addKnockDownPins(10);
        finalFrame.addKnockDownPins(10);
        assertThat(finalFrame.isPossiblePitching()).isTrue();
        assertThat(finalFrame.getBowlingPins()).size().isEqualTo(2);
        assertThat(finalFrame.getCountOfPitching()).isEqualTo(3);
    }

    @Test
    @DisplayName("맞춘 핀을 리스트에 다음 - 1투구(스트라이크), 2투구(스트라이크), 3투구(스트라이크)")
    void addBowlingPinsBonus() {
        FinalFrame finalFrame = new FinalFrame();
        finalFrame.addKnockDownPins(10);
        finalFrame.addKnockDownPins(10);
        finalFrame.addKnockDownPins(10);
        assertThat(finalFrame.isPossiblePitching()).isFalse();
        assertThat(finalFrame.getBowlingPins()).size().isEqualTo(3);
        assertThat(finalFrame.getCountOfPitching()).isEqualTo(3);
    }

}