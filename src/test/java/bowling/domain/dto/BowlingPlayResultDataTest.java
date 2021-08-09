package bowling.domain.dto;

import bowling.domain.player.Bowler;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BowlingPlayResultDataTest {

    @DisplayName("Bowler정보로 BowlingPlayResultData를 생성한다")
    @Test
    void should_make_player_using_3_char() {
        //arrange
        Bowler bowler = Bowler.of("abc");

        //act, assert
        assertThat(BowlingPlayResultData.of(bowler)).isInstanceOf(BowlingPlayResultData.class);
    }

    @DisplayName("BowlingPlayResultData는 playerName을 반환한다")
    @Test
    public void should_return_name() throws Exception {
        //arrange
        Bowler bowler = Bowler.of("abc");

        //act
        BowlingPlayResultData data = BowlingPlayResultData.of(bowler);

        //assert
        assertThat(data.getPlayerName()).isEqualTo("abc");
    }

}