package bowling.frame.state;

import bowling.score.Pin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class MissTest {

    private Pin firstPins;
    private Pin secondPins;
    private State secondState;

    @Test
    @DisplayName("투구 종료 여부 확인 - 종료")
    void isFinished() {
        firstPins = Pin.bowl("4");
        secondPins = Pin.bowl("3");
        secondState = Miss.of(firstPins, secondPins);
        assertThat(secondState.isFinish()).isTrue();
    }

    @Test
    @DisplayName("첫번째, 두번째 볼링 결과 - 미스")
    void result() {
        firstPins = Pin.bowl("4");
        secondPins = Pin.bowl("3");
        secondState = Miss.of(firstPins, secondPins);
        assertThat(secondState.getBowlResults().get(0)).isEqualTo("4");
        assertThat(secondState.getBowlResults().get(1)).isEqualTo("3");
    }

    @Test
    @DisplayName("Next에서 진행된 볼링이 Miss 상태인지 확인")
    void stateIsMiss() {
        firstPins = Pin.bowl("4");
        secondPins = Pin.bowl("3");
        secondState = Next.of(firstPins).bowl(secondPins);
        assertThat(secondState instanceof Miss).isTrue();
    }

    @Test
    @DisplayName("이전 볼링의 결과가 거터라면..")
    void prevBowlIsGutter() {
        firstPins = Pin.bowl("0");
        secondPins = Pin.bowl("5");
        secondState = Next.of(firstPins).bowl(secondPins);
        assertThat(secondState.getBowlResults().get(0)).isEqualTo("-");
        assertThat(secondState.getBowlResults().get(1)).isEqualTo("5");
    }

    @Test
    @DisplayName("현재 볼링의 결과가 거터라면..")
    void prevBowlMark() {
        firstPins = Pin.bowl("5");
        secondPins = Pin.bowl("0");
        secondState = Next.of(firstPins).bowl(secondPins);
        assertThat(secondState.getBowlResults().get(0)).isEqualTo("5");
        assertThat(secondState.getBowlResults().get(1)).isEqualTo("-");
    }

    @Test
    @DisplayName("모둔 볼링의 결과가 거터라면..")
    void allBowlMarkIsGutter() {
        firstPins = Pin.bowl("0");
        secondPins = Pin.bowl("0");
        secondState = Next.of(firstPins).bowl(secondPins);
        assertThat(secondState.getBowlResults().get(0)).isEqualTo("-");
        assertThat(secondState.getBowlResults().get(1)).isEqualTo("-");
    }

    @Test
    @DisplayName("프레임 종료 후 추가 투구 시 Exception 발생")
    void unSupportedBowl() {
        assertThatExceptionOfType(UnsupportedOperationException.class)
                .isThrownBy(() -> secondState = Miss.of(Pin.bowl("4"),
                                                        Pin.bowl("3"))
                        .bowl(Pin.bowl("5")));
    }

}
