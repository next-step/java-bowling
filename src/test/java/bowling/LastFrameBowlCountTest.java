package bowling;

import bowling.entity.frame.LastFrameBowlCount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class LastFrameBowlCountTest {
    @Test
    @DisplayName("마지막 프레임 볼링 카운트 생성")
    public void createBowlCount() {
        LastFrameBowlCount lastFrameBowlCount = new LastFrameBowlCount();

        assertThat(lastFrameBowlCount.equals(new LastFrameBowlCount())).isTrue();
    }

    @Test
    @DisplayName("2번 진행 시 종료")
    public void twoBowlGameEnd() {
        LastFrameBowlCount lastFrameBowlCount = new LastFrameBowlCount();

        lastFrameBowlCount.bowl();
        lastFrameBowlCount.bowl();

        assertThat(lastFrameBowlCount.bowlingGameEnd()).isTrue();
    }

    @Test
    @DisplayName("3번 진행 가능할 경우 3번 진행 시 종료")
    public void threeBowlGameEnd() {
        LastFrameBowlCount lastFrameBowlCount = new LastFrameBowlCount();

        lastFrameBowlCount.maxCountThree();

        lastFrameBowlCount.bowl();
        lastFrameBowlCount.bowl();
        lastFrameBowlCount.bowl();

        assertThat(lastFrameBowlCount.bowlingGameEnd()).isTrue();
    }

}
