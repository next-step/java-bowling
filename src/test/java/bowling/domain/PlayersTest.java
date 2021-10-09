package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("NonAsciiCharacters")
class PlayersTest {

    @Test
    public void Players를_만들_수_있다(){
        //given
        Players players = Players.create();
        //when, then
        assertThat(players).isEqualTo(Players.create());
    }

    @Test
    public void Player를_추가할_수_있다(){
        //given
        Players players = Players.create();
        //when
        players.add(Player.from("sml"));
        //then
        assertThat(players).isEqualTo(Players.from("sml"));
    }

}