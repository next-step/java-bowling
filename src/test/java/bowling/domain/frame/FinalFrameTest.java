package bowling.domain.frame;

import static org.assertj.core.api.Assertions.assertThat;

import bowling.Pin;
import bowling.domain.progress.Closed;
import bowling.domain.progress.GeneralProgress;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FinalFrameTest {


    private static final Pin MISS = Pin.of(0);
    private static final Pin FIRST_SPARE = Pin.of(3);
    private static final Pin SPARE = Pin.of(7);
    private static final Pin NORMAL = Pin.of(5);
    private static final Pin STRIKE = Pin.of(10);

    private FinalFrame finalFrame;

    @BeforeEach
    void init() {
        finalFrame = new FinalFrame();
    }

    @Test
    @DisplayName("첫 투구가 Strike시 두번 더 투구할 수 있다.")
    void bowlStrikeTest() {
        assertThat(finalFrame.bowl(STRIKE)).isInstanceOf(GeneralProgress.class);
        assertThat(finalFrame.bowl(STRIKE)).isInstanceOf(GeneralProgress.class);
        assertThat(finalFrame.bowl(STRIKE)).isInstanceOf(Closed.class);
    }

    @Test
    @DisplayName("일반 투구시, 해당 프레임은 다음 프레임으로 넘어간다.")
    void bowlGeneralTest() {
        assertThat(finalFrame.bowl(NORMAL)).isInstanceOf(GeneralProgress.class);
    }

    @Test
    @DisplayName("MISS 시, 해당 프레임은 다음 프레임으로 넘어간다.")
    void bowlMissTest() {
        assertThat(finalFrame.bowl(MISS)).isInstanceOf(GeneralProgress.class);
    }

    @Test
    @DisplayName("투구시 해당 프레임내 결과가 저장된다.")
    void bowlStatueTest() {
        finalFrame.bowl(NORMAL);
        assertThat(finalFrame.getResults()).hasSize(1);

        finalFrame.bowl(NORMAL);
        assertThat(finalFrame.getResults()).hasSize(2);
    }

    @Test
    @DisplayName("2회 투구 안에, Strike가 있다면, 3번의 시도가 가능하다.")
    void bowlBonusRoundStrikeTest() {
        assertThat(finalFrame.bowl(STRIKE)).isInstanceOf(GeneralProgress.class);
        assertThat(finalFrame.bowl(MISS)).isInstanceOf(GeneralProgress.class);
        assertThat(finalFrame.bowl(STRIKE)).isInstanceOf(Closed.class);
    }

    @Test
    @DisplayName("2회 투구 가 Spare이 있다면, 3번의 시도가 가능하다.")
    void bowlBonusRoundSpareTest() {
        assertThat(finalFrame.bowl(FIRST_SPARE)).isInstanceOf(GeneralProgress.class);
        assertThat(finalFrame.bowl(SPARE)).isInstanceOf(GeneralProgress.class);
        assertThat(finalFrame.bowl(STRIKE)).isInstanceOf(Closed.class);
    }

    @Test
    @DisplayName("2회 투구 가 Spare 또는 Strike가 없다면, 2번의 시도만 가능하다.")
    void bowlNotBonusRoundTest() {
        assertThat(finalFrame.bowl(FIRST_SPARE)).isInstanceOf(GeneralProgress.class);
        assertThat(finalFrame.bowl(NORMAL)).isInstanceOf(Closed.class);
    }
}
