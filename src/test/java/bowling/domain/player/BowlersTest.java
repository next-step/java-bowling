package bowling.domain.player;

import bowling.exception.NullArgumentException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BowlersTest {

    public static final List<String> PLAYER_NAMES = Arrays.asList("aaa", "bbb", "ccc");

    @DisplayName("이름 리스트로 Bowlers를 만들수있다")
    @Test
    void should_make_bowlers() {
        //arrange, act, assert
        assertThat(Bowlers.of(PLAYER_NAMES)).isInstanceOf(Bowlers.class);
    }

    @DisplayName("이름 리스트가 null 일 경우 NullArgumentException 예외가 발생한다")
    @Test
    void should_throw_exception() {
        assertThatThrownBy(() -> Bowlers.of(null)).isInstanceOf(NullArgumentException.class);
    }

    @DisplayName("첫번째 플레이어 이름을 반환한다")
    @Test
    void firstPlayer() {
        //arrange
        Bowlers bowlers = Bowlers.of(PLAYER_NAMES);

        //act, assert
        assertThat(bowlers.firstBowler().getName()).isEqualTo(PLAYER_NAMES.get(0));
    }

    @DisplayName("bowler를 프레임 끝날대 마다 전환한다")
    @Test
    void rotatePlayer() {
        //arrange
        Bowlers bowlers = Bowlers.of(PLAYER_NAMES);

        //act, assert
        Bowler currentBowler = bowlers.firstBowler();
        assertThat(currentBowler.getName()).isEqualTo(PLAYER_NAMES.get(0));

        Bowler nextBowler = bowlers.changeBowler(currentBowler);
        assertThat(nextBowler.getName()).isEqualTo(PLAYER_NAMES.get(1));

        currentBowler = nextBowler;
        nextBowler = bowlers.changeBowler(currentBowler);
        assertThat(nextBowler.getName()).isEqualTo(PLAYER_NAMES.get(2));

        currentBowler = nextBowler;
        nextBowler = bowlers.changeBowler(currentBowler);
        assertThat(nextBowler.getName()).isEqualTo(PLAYER_NAMES.get(0));
    }
}