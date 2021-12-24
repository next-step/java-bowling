package bowling.domain.frame;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import bowling.domain.Pin;
import bowling.domain.progress.Closed;
import bowling.domain.progress.GeneralProgress;
import bowling.domain.progress.ProgressFactory;
import bowling.domain.result.status.HitNumber;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FinalFrameTest {


    private static final Pin GUTTER = Pin.of(0);
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
    @DisplayName("일반 프레임에서는 점수의 합계가 10점을 넘길 수 없다.")
    void createValidTest() {
        assertThatIllegalArgumentException().isThrownBy(
            () -> new FinalFrame(ProgressFactory.closed(),
                Arrays.asList(mockSixHitNumber(), mockSixHitNumber())));
    }

    @Test
    @DisplayName("첫 투구가 Strike시 두번 더 투구할 수 있다.")
    void bowlStrikeTest() {
        Frame bowl = finalFrame.bowl(STRIKE);
        assertThat(bowl.getProgress()).isInstanceOf(GeneralProgress.class);

        Frame bowl2 = bowl.bowl(STRIKE);
        assertThat(bowl2.getProgress()).isInstanceOf(GeneralProgress.class);

        assertThat(bowl2.bowl(STRIKE).getProgress()).isInstanceOf(Closed.class);
    }

    @Test
    @DisplayName("일반 투구시, 해당 프레임은 다음 프레임으로 넘어간다.")
    void bowlGeneralTest() {
        assertThat(finalFrame.bowl(NORMAL).getProgress()).isInstanceOf(GeneralProgress.class);
    }

    @Test
    @DisplayName("Gutter 시, 해당 프레임은 다음 프레임으로 넘어간다.")
    void bowlMissTest() {
        assertThat(finalFrame.bowl(GUTTER).getProgress()).isInstanceOf(GeneralProgress.class);
    }

    @Test
    @DisplayName("Gutter가 2회 인경우, Miss를 반환한다.")
    void bowlGutterToMissTest() {
        Frame bowl = finalFrame.bowl(GUTTER).bowl(GUTTER);
        assertThat(bowl.getProgress()).isInstanceOf(Closed.class);

        assertThat(bowl.getResults().size()).isEqualTo(1);
    }

    @Test
    @DisplayName("Gutter가 1회 인경우, Miss를 반환하지 않는다.")
    void bowlGutterNotMissTest() {
        Frame bowl = finalFrame.bowl(NORMAL).bowl(GUTTER);
        assertThat(bowl.getProgress()).isInstanceOf(Closed.class);

        assertThat(bowl.getResults().size()).isEqualTo(2);
    }


    @Test
    @DisplayName("투구시 해당 프레임내 결과가 저장된다.")
    void bowlStatueTest() {
        finalFrame.bowl(NORMAL);
        assertThat(finalFrame.getResults().size()).isEqualTo(1);

        finalFrame.bowl(NORMAL);
        assertThat(finalFrame.getResults().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("2회 투구 안에, Strike가 있다면, 3번의 시도가 가능하다.")
    void bowlBonusRoundStrikeTest() {
        Frame bowl = finalFrame.bowl(STRIKE);
        assertThat(bowl.getProgress()).isInstanceOf(GeneralProgress.class);

        Frame bowl2 = bowl.bowl(GUTTER);
        assertThat(bowl2.getProgress()).isInstanceOf(GeneralProgress.class);

        assertThat(bowl2.bowl(STRIKE).getProgress()).isInstanceOf(Closed.class);
    }

    @Test
    @DisplayName("2회 투구 가 Spare이 있다면, 3번의 시도가 가능하다.")
    void bowlBonusRoundSpareTest() {
        Frame bowl = finalFrame.bowl(FIRST_SPARE);
        assertThat(bowl.getProgress()).isInstanceOf(GeneralProgress.class);

        Frame bowl2 = bowl.bowl(SPARE);
        assertThat(bowl2.getProgress()).isInstanceOf(GeneralProgress.class);

        assertThat(bowl2.bowl(STRIKE).getProgress()).isInstanceOf(Closed.class);
    }

    @Test
    @DisplayName("2회 투구 가 Spare 또는 Strike가 없다면, 2번의 시도만 가능하다.")
    void bowlNotBonusRoundTest() {
        Frame bowl = finalFrame.bowl(FIRST_SPARE);
        assertThat(bowl.getProgress()).isInstanceOf(GeneralProgress.class);

        assertThat(bowl.bowl(NORMAL).getProgress()).isInstanceOf(Closed.class);
    }

    private HitNumber mockSixHitNumber() {
        return new HitNumber(Pin.of(6));
    }
}
