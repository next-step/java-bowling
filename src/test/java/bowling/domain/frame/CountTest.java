package bowling.domain.frame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CountTest {
    private Count count;

    @BeforeEach
    void setUp() {
        count = Count.ofFirst();
    }

    @DisplayName("카운트 객체를 생성할 수 있다.")
    @Test
    void create() {
        Count expect = Count.ofFirst();

        assertThat(count).isEqualTo(expect);
    }

    @DisplayName("NormalFrame 카운트를 증가할 수 있다.")
    @Test
    void increaseNormalFrameCount() {
        Count expect = Count.ofFirst();
        expect = expect.increaseNormalFrameCount();

        Count actual = count.increaseNormalFrameCount();
        assertThat(actual).isEqualTo(expect);
    }

    @DisplayName("보너스 카운트가 아닌지 확인할 수 있다.")
    @Test
    void isNotBonusCount() {
        count = count.increaseNormalFrameCount();
        Count actual = count.increaseNormalFrameCount();

        assertThat(actual.isNotBonusCount()).isTrue();
    }

    @DisplayName("NormalFrame 카운트를 증가할 수 있다.")
    @Test
    void increaseFinalFrameCount() {
        Count expect = Count.ofFirst();
        expect = expect.increaseFinalFrameCount();

        Count actual = count.increaseFinalFrameCount();
        assertThat(actual).isEqualTo(expect);
    }

    @DisplayName("보너스 카운트를 확인할 수 있다.")
    @Test
    void isEndBonus() {
        count = count.increaseNormalFrameCount();
        count = count.increaseNormalFrameCount();
        Count actual = count.increaseNormalFrameCount();

        assertThat(actual.isBonusCount()).isTrue();
    }
}