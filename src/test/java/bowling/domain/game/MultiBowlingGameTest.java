package bowling.domain.game;

import bowling.domain.score.PitchScore;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MultiBowlingGameTest {

    private List<String> playerNames = Arrays.asList("1번!", "2번!", "3번!");

    @DisplayName("List<String> 플레이어 이름 명단을 받으면 그 크기만큼의 List<SingleBowlingGame> 컬렉션을 생성함")
    @Test
    public void makeMultiBowlingGame() {
        MultiBowlingGame multiBowlingGame = MultiBowlingGame.of(playerNames);

        assertThat(multiBowlingGame.getPlayerCounts()).isEqualTo(3);
    }

    @DisplayName("볼링 게임  시작시, 1번 플레이어가 가장 먼저 플레이함")
    @Test
    public void currentPlayer_1번() {
        MultiBowlingGame multiBowlingGame = MultiBowlingGame.of(playerNames);

        SingleBowlingGame singleBowlingGame = multiBowlingGame.getCurrentGame();

        assertThat(singleBowlingGame.getPlayerName()).isEqualTo("1번!");
    }

    @DisplayName("1번 플레이어가 현재 프레임의 투구를 완료하면 다음 턴은 2번 플레이어가 가짐")
    @Test
    public void currentPlayer_2번() {
        MultiBowlingGame multiBowlingGame = MultiBowlingGame.of(playerNames);
        multiBowlingGame.bowl(PitchScore.valueOf(10));

        SingleBowlingGame singleBowlingGame = multiBowlingGame.getCurrentGame();

        assertThat(singleBowlingGame.getPlayerName()).isEqualTo("2번!");
    }

    @DisplayName("3번 플레이어까지 투구를 완료하고 nextFrame으로 이동 요청을 하면 다시 1번 플레이어의 턴으로 돌아옴")
    @Test
    public void currentPlayer_다시_1번() {
        MultiBowlingGame multiBowlingGame = MultiBowlingGame.of(playerNames);
        multiBowlingGame.bowl(PitchScore.valueOf(10));
        multiBowlingGame.bowl(PitchScore.valueOf(10));
        multiBowlingGame.bowl(PitchScore.valueOf(10));

        multiBowlingGame.moveToNextFrame();

        SingleBowlingGame singleBowlingGame = multiBowlingGame.getCurrentGame();

        assertThat(singleBowlingGame.getPlayerName()).isEqualTo("1번!");
    }


    @DisplayName("1번, 3번 플레이어가 스트라이크를 치고 2번은 프레임을 마무리 못한 경우 현재 플레이어는 2번")
    @Test
    public void currentPlayer_2번_미완성() {
        MultiBowlingGame multiBowlingGame = MultiBowlingGame.of(playerNames);
        multiBowlingGame.bowl(PitchScore.valueOf(10));
        multiBowlingGame.bowl(PitchScore.valueOf(3));

        SingleBowlingGame singleBowlingGame = multiBowlingGame.getCurrentGame();

        assertThat(singleBowlingGame.getPlayerName()).isEqualTo("2번!");
    }

    @DisplayName("모든 플레이어가 n번 프레임의 투구를 완료하지 못하면 n+1 프레임으로 넘어가지 못하기 때문에 1번 플레이어의 기회로 넘어가지 못함")
    @Test
    public void moveToNextFrame_불가능() {
        MultiBowlingGame multiBowlingGame = MultiBowlingGame.of(playerNames);
        multiBowlingGame.bowl(PitchScore.valueOf(10));
        multiBowlingGame.bowl(PitchScore.valueOf(10));
        multiBowlingGame.bowl(PitchScore.valueOf(3));

        multiBowlingGame.moveToNextFrame();

        SingleBowlingGame singleBowlingGame = multiBowlingGame.getCurrentGame();

        assertThat(singleBowlingGame.getPlayerName()).isEqualTo("3번!");
    }

    @DisplayName("모든 플레이어가 10프레임까지 완료하면 nextTurn이 존재하지 앟는다")
    @Test
    public void hasNextTurn_False() {
        MultiBowlingGame multiBowlingGame = MultiBowlingGame.of(playerNames);
        for (int i = 0; i < 12; i++) {
            multiBowlingGame.bowl(PitchScore.valueOf(10));
            multiBowlingGame.bowl(PitchScore.valueOf(10));
            multiBowlingGame.bowl(PitchScore.valueOf(10));
            multiBowlingGame.moveToNextFrame();
        }

        assertThat(multiBowlingGame.hasNextTurn()).isFalse();
    }

}
