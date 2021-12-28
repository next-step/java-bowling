package bowling.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class NormalFrameTest {

    private static final int STRIKE_PITCHING = 1;
    private static final int NORMAL_PITCHING = 2;

    private NormalFrame normalFrame;

    @BeforeEach
    void setUp() {
        normalFrame = new NormalFrame();
    }

    @Test
    @DisplayName("노멀프레임 객체 생성")
    void create() {
        assertThat(normalFrame).isEqualTo(new NormalFrame());
    }

    @Test
    @DisplayName("스트라이크 x -> 첫번째 투구 + 두번째 투구 핀의 합이 11이상이면 IllegalArgumentException 반환")
    void invalid() {
        normalFrame.pitching(7);
        assertThatThrownBy(() -> normalFrame.pitching(4))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("스트라이크 o -> 두번째 투구를 할 경우, IllegalArgumentException 반환")
    void invalidStrike() {
        normalFrame.pitching(10);
        assertThatThrownBy(() -> normalFrame.pitching(4))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("스트라이크 x -> 첫번째 투구 + 두번째 투구 핀의 합이 10이하이면 정상")
    void valid() {
        normalFrame.pitching(7);
        assertThatCode(() -> normalFrame.pitching(3))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("1투구(3), 2투구(가능)")
    void pitching() {
        normalFrame.pitching(3);
        assertThat(normalFrame.getPitching()).isEqualTo(NORMAL_PITCHING);
        assertThat(normalFrame.getCountOfPitching()).isEqualTo(1);
    }

    @Test
    @DisplayName("1투구(스트라이크), 2투구(불가능)")
    void pitchingStrike() {
        normalFrame.pitching(10);
        assertThat(normalFrame.getPitching()).isEqualTo(STRIKE_PITCHING);
        assertThat(normalFrame.getCountOfPitching()).isEqualTo(1);
    }

    @Test
    @DisplayName("1투구(8), 2투구(스페어)")
    void pitchingSpare() {
        normalFrame.pitching(8);
        normalFrame.pitching(2);
        assertThat(normalFrame.getPitching()).isEqualTo(NORMAL_PITCHING);
        assertThat(normalFrame.getCountOfPitching()).isEqualTo(2);
    }

    @Test
    @DisplayName("투구가 가능한지 테스트")
    void isPossiblePitching() {
        normalFrame.pitching(5);
        assertThat(normalFrame.isPossiblePitching()).isTrue();
    }

    @Test
    @DisplayName("투구 불가능한지 테스트 - 스트라이크일 경우 false")
    void isImpossiblePitching() {
        normalFrame.pitching(10);
        assertThat(normalFrame.isPossiblePitching()).isFalse();
    }

    @Test
    @DisplayName("투구 불가능한지 테스트 - 2번 던졌을 경우 false")
    void isImpossiblePitching2() {
        normalFrame.pitching(8);
        normalFrame.pitching(1);
        assertThat(normalFrame.isPossiblePitching()).isFalse();
    }

    @Test
    @DisplayName("몇번째 투구인지 입력받아 맞춘 핀 가져오기")
    void getCountOfHits() {
        normalFrame.pitching(8);
        assertThat(normalFrame.getCountOfHits(0)).isEqualTo(8);
    }

}