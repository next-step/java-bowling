package bowling.domain.player;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BowlerTest {

    @DisplayName("이름 3자로 String으로 player 객체를 만들 수 있다")
    @Test
    void should_make_bowler_with_player_frames() {
        //arrange, act, assert
        assertThat(Bowler.of("abc")).isInstanceOf(Bowler.class);
    }

    @DisplayName("player는 name을 반환할 수 있다")
    @Test
    void should_return_bowler_name() {
        //arrange
        Bowler bowler = Bowler.of("abc");

        //act
        String name = bowler.getName();

        //assert
        assertThat(name).isEqualTo("abc");
    }

}