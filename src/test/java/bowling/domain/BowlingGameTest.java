package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BowlingGameTest {

    @DisplayName("주어진 만큼 핀을 쓰러뜨린다")
    @Test
    public void play_success() throws Exception {
        //given
        Player player = new Player("aaa");
        BowlingGame game = new BowlingGame(player);

        //then
        game.play(10);
    }


    @DisplayName("마지막 프레임 까지 공을 던지면 게임이 완료 상태가 된다")
    @Test
    public void isFinish_success() throws Exception {
        //given
        Player player = new Player("aaa");
        BowlingGame game = new BowlingGame(player);

        //then
        game.play(10);
        game.play(10);
        game.play(10);
        game.play(10);
        game.play(10);
        game.play(10);
        game.play(10);
        game.play(10);
        game.play(10);
        game.play(10);
        game.play(10);

        assertTrue(game.isFinish());
    }

    @DisplayName("현재 진행된 프레임의 수를 반환 한다.")
    @Test
    public void getFrameSize_success() throws Exception {
        //given
        BowlingGame game = new BowlingGame(new Player("aaa"));

        //when
        game.play(3);
        game.play(3);
        game.play(3);

        //then
        assertThat(game.getFrameSize()).isEqualTo(3);
    }
}
