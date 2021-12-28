package bowling.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class FinalFrameTest {

    private static final int NORMAL_PITCHING = 2;
    private static final int BONUS_PITCHING = 3;
    private FinalFrame finalFrame;

    @BeforeEach
    void setUp() {
        finalFrame = new FinalFrame();
    }

    @Test
    @DisplayName("파이널프레임 객체 생성")
    void create() {
        assertThat(finalFrame).isEqualTo(new FinalFrame());
    }

    @Test
    @DisplayName("스트라이크 x -> 첫번째 투구 + 두번째 투구 핀의 합이 11이상이면 IllegalArgumentException 반환")
    void invalidSecond() {
        FinalFrame finalFrame = new FinalFrame();
        finalFrame.pitching(7);
        assertThatThrownBy(() -> finalFrame.pitching(4))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("보너스 라운드 스트라이크 X -> 두번째 투구 + 세번째 투구 핀의 합이 11이상이면 IllegalArgumentException 반환")
    void invalidThird() {
        finalFrame.pitching(10);
        finalFrame.pitching(7);
        assertThatThrownBy(() -> finalFrame.pitching(4))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("보너스 라운드 -> 4번 이상 던지면 IllegalArgumentException 반환")
    void invalidThirdStrike() {
        finalFrame.pitching(10);
        finalFrame.pitching(7);
        finalFrame.pitching(1);
        assertThatThrownBy(() -> finalFrame.pitching(3))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("보너스 라운드(스트라이크), 두번째 투구 + 세번째 투구 합이 10이하이면 정상")
    void valid() {
        finalFrame.pitching(10);
        finalFrame.pitching(7);
        assertThatCode(() -> finalFrame.pitching(3))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("보너스 라운드(스페어), 세번째 투구 10이하이면 정상")
    void valid1() {
        finalFrame.pitching(3);
        finalFrame.pitching(7);
        assertThatCode(() -> finalFrame.pitching(4))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("1투구(8), 2투구(1), 3투구(보너스 없음)")
    void addBowlingPins() {
        finalFrame.pitching(8);
        finalFrame.pitching(1);
        assertThat(finalFrame.isPossiblePitching()).isFalse();
        assertThat(finalFrame.getCountOfPitching()).isEqualTo(2);
        assertThat(finalFrame.getPitching()).isEqualTo(NORMAL_PITCHING);
    }

    @Test
    @DisplayName("1투구(5), 2투구(스페어), 3투구(보너스 있음)")
    void addBowlingPinsSpare() {
        finalFrame.pitching(5);
        finalFrame.pitching(5);
        assertThat(finalFrame.isPossiblePitching()).isTrue();
        assertThat(finalFrame.getCountOfPitching()).isEqualTo(2);
        assertThat(finalFrame.getPitching()).isEqualTo(BONUS_PITCHING);
    }

    @Test
    @DisplayName("1투구(스트라이크), 2투구(보너스있음)")
    void addBowlingPinsStrike() {
        finalFrame.pitching(10);
        assertThat(finalFrame.isPossiblePitching()).isTrue();
        assertThat(finalFrame.getCountOfPitching()).isEqualTo(1);
        assertThat(finalFrame.getPitching()).isEqualTo(BONUS_PITCHING);
    }

    @Test
    @DisplayName("맞춘 핀을 리스트에 다음 - 1투구(스트라이크), 2투구(스트라이크), 3투구(스트라이크)")
    void addBowlingPinsBonus() {
        finalFrame.pitching(10);
        finalFrame.pitching(10);
        finalFrame.pitching(5);
        assertThat(finalFrame.isPossiblePitching()).isFalse();
        assertThat(finalFrame.getCountOfPitching()).isEqualTo(3);
        assertThat(finalFrame.getPitching()).isEqualTo(BONUS_PITCHING);
    }

    @Test
    @DisplayName("투구 가능한지 테스트 - 투구x true")
    void isPossiblePitching11() {
        assertThat(finalFrame.isPossiblePitching()).isTrue();
    }

    @Test
    @DisplayName("투구 가능한지 테스트 - 첫번째 투구일 경우 true")
    void isPossiblePitching1() {
        finalFrame.pitching(1);
        assertThat(finalFrame.isPossiblePitching()).isTrue();
    }

    @Test
    @DisplayName("투구 가능한지 테스트 - 첫번째, 스트라이크 던졌을 경우 true")
    void isPossiblePitching2() {
        finalFrame.pitching(10);
        finalFrame.pitching(7);
        assertThat(finalFrame.isPossiblePitching()).isTrue();
    }

    @Test
    @DisplayName("투구 가능한지 테스트 - 스페어 던졌을 경우 true")
    void isPossiblePitching3() {
        finalFrame.pitching(3);
        finalFrame.pitching(7);
        assertThat(finalFrame.isPossiblePitching()).isTrue();
    }

    @Test
    @DisplayName("투구 불가능한지 테스트 - 스페어 미처리 false")
    void isImpossiblePitching4() {
        finalFrame.pitching(3);
        finalFrame.pitching(2);
        assertThat(finalFrame.isPossiblePitching()).isFalse();
    }

    @Test
    @DisplayName("투구 불가능한지 테스트 - 2번 스트라이크 던졌을 경우 false")
    void isImpossiblePitching1() {
        finalFrame.pitching(10);
        finalFrame.pitching(10);
        assertThat(finalFrame.isPossiblePitching()).isFalse();
    }

    @Test
    @DisplayName("투구 불가능한지 테스트 - 3번 던졌을 경우 false")
    void isImpossiblePitching2() {
        finalFrame.pitching(10);
        finalFrame.pitching(7);
        finalFrame.pitching(3);
        assertThat(finalFrame.isPossiblePitching()).isFalse();
    }


}