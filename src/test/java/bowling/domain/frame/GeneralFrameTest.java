package bowling.domain.frame;

import static org.assertj.core.api.Assertions.*;

import bowling.Pin;
import bowling.domain.progress.Closed;
import bowling.domain.progress.GeneralProgress;
import bowling.domain.progress.ProgressFactory;
import bowling.domain.state.end.first.HitNumber;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GeneralFrameTest {

    private static final Pin GUTTER = Pin.of(0);
    private static final Pin NORMAL = Pin.of(5);
    private static final Pin STRIKE = Pin.of(10);

    private GeneralFrame generalFrame;

    @BeforeEach
    void init() {
        generalFrame = new GeneralFrame();
    }

    @Test
    @DisplayName("일반 프레임에서는 점수의 합계가 10점을 넘길 수 없다.")
    void createValidTest() {
        assertThatIllegalArgumentException().isThrownBy(
            () -> new GeneralFrame(ProgressFactory.closed(),
                Arrays.asList(mockSixHitNumber(), mockSixHitNumber())));
    }


    @Test
    @DisplayName("Strike시 해당 프레임은 종료된다.")
    void bowlStrikeTest() {
        assertThat(generalFrame.bowl(STRIKE).getProgress()).isInstanceOf(Closed.class);
    }

    @Test
    @DisplayName("일반 투구시, 해당 프레임은 다음 프레임으로 넘어간다.")
    void bowlGeneralTest() {
        assertThat(generalFrame.bowl(NORMAL).getProgress()).isInstanceOf(GeneralProgress.class);
    }

    @Test
    @DisplayName("Gutter 시, 해당 프레임은 다음 프레임으로 넘어간다.")
    void bowlGutterTest() {
        assertThat(generalFrame.bowl(GUTTER).getProgress()).isInstanceOf(GeneralProgress.class);
    }

    @Test
    @DisplayName("Strike가 아닌경우 최대 2번의 시도가 가능하다.")
    void bowlTryCountTest() {
        Frame bowl = generalFrame.bowl(NORMAL);
        assertThat(bowl.getProgress()).isInstanceOf(GeneralProgress.class);
        assertThat(bowl.bowl(NORMAL).getProgress()).isInstanceOf(Closed.class);
    }

    @Test
    @DisplayName("Gutter가 2회 인경우, Miss를 반환한다.")
    void bowlGutterToMissTest() {
        Frame bowl = generalFrame.bowl(GUTTER).bowl(GUTTER);
        assertThat(bowl.getProgress()).isInstanceOf(Closed.class);

        assertThat(bowl.getResults()).hasSize(1);
    }

    @Test
    @DisplayName("Gutter가 1회 인경우, Miss를 반환하지 않는다.")
    void bowlGutterNotMissTest() {
        Frame bowl = generalFrame.bowl(NORMAL).bowl(GUTTER);
        assertThat(bowl.getProgress()).isInstanceOf(Closed.class);

        assertThat(bowl.getResults()).hasSize(2);
    }


    @Test
    @DisplayName("투구시 해당 프레임내 결과가 저장된다.")
    void bowlStatueTest() {
        generalFrame.bowl(NORMAL);
        assertThat(generalFrame.getResults()).hasSize(1);

        generalFrame.bowl(NORMAL);
        assertThat(generalFrame.getResults()).hasSize(2);
    }

    private HitNumber mockSixHitNumber() {
        return new HitNumber(Pin.of(6));
    }
}