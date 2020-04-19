package bowling.domain;

import bowling.domain.score.Score;
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

    @DisplayName("점수 합산해야 하는데 다음 프레임이 없을 경우 현재 점수로 리턴")
    @Test
    public void getFrameScore_success_null() throws Exception {
        //given
        BowlingGame game = new BowlingGame(new Player("aaa"));
        game.play(10);

        //when
        Score score = game.getTotalScore(1);

        //then
        assertThat(score).isEqualTo(new Score(10));
    }

    @DisplayName("다음 프레임을 확인하여 strike 점수 계산")
    @Test
    public void getFrameScore_success_strike() throws Exception {
        //given
        BowlingGame game = new BowlingGame(new Player("aaa"));
        game.play(10);
        game.play(5);
        game.play(2);

        //when
        Score score = game.getTotalScore(1);

        //then
        assertThat(score).isEqualTo(new Score(17));
    }

    @DisplayName("다음 프레임을 확인하여 spare 점수 계산")
    @Test
    public void getFrameScore_success_spare() throws Exception {
        //given
        BowlingGame game = new BowlingGame(new Player("aaa"));
        game.play(5);
        game.play(5);
        game.play(2);

        //when
        Score score = game.getTotalScore(1);
        Score score2 = game.getTotalScore(2);

        //then
        assertThat(score).isEqualTo(new Score(12));
        assertThat(score2).isEqualTo(new Score(14));
    }

    @Test
    public void getTotalScore_success() throws Exception {
        //given
        BowlingGame game = new BowlingGame(new Player("aaa"));
        game.play(5);
        game.play(5);
        game.play(2);

        //when
        Score totalScore = game.getTotalScore(2);

        //then
        assertThat(totalScore).isEqualTo(new Score(14));
    }
}
