package bowling.frame.state;

import bowling.score.Pin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MissTest {

    @Test
    @DisplayName("첫번째, 두번째 볼링 결과 - 미스")
    void result() {
        Pin prevPins = Pin.bowl("4");
        Pin nextPins = Pin.bowl("3");
        State state = Miss.of(prevPins, nextPins);
        assertThat(state.toString()).isEqualTo("4|3");
    }

    @Test
    @DisplayName("이전 볼링의 결과가 거터라면..")
    void prevBowlIsGutter() {
        Pin firstPins = Pin.bowl("0");
        State secondState = Next.bowl(firstPins).bowl("5");
        assertThat(secondState.toString()).isEqualTo("-|5");
    }

    @Test
    @DisplayName("현재 볼링의 결과가 거터라면..")
    void prevBowlMark() {
        Pin firstPins = Pin.bowl("5");
        State secondState = Next.bowl(firstPins).bowl("0");
        assertThat(secondState.toString()).isEqualTo("5|-");
    }

    @Test
    @DisplayName("모둔 볼링의 결과가 거터라면..")
    void allBowlMarkIsGutter() {
        Pin firstPins = Pin.bowl("0");
        State secondState = Next.bowl(firstPins).bowl("0");
        assertThat(secondState.toString()).isEqualTo("-|-");
    }
}
