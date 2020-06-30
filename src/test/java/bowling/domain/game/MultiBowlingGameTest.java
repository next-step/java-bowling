package bowling.domain.game;

import bowling.domain.dto.BowlingGameDto;
import bowling.domain.exception.BowlingBuildingException;
import bowling.domain.score.PitchScore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MultiBowlingGameTest {

    private List<String> playerNames = Arrays.asList("1번!", "2번!", "3번!");
    private MultiBowlingGame multiBowlingGame;

    @BeforeEach
    public void setMultiBowlingGame() {
        multiBowlingGame = MultiBowlingGame.of(playerNames);
    }
    @DisplayName("player가 0명이라면 예외 발생")
    @Test
    public void makeMultiBowlingGame_예외() {
        assertThatThrownBy(() -> {
            MultiBowlingGame.of(new ArrayList<>());
        }).isInstanceOf(BowlingBuildingException.class)
                .hasMessageContaining(BowlingBuildingException.INVALID_PLAYER_COUNTS);
    }

    @DisplayName("List<String> 플레이어 이름 명단을 받으면 그 크기 만큼의 List<SingleBowlingGame> 컬렉션을 생성함")
    @Test
    public void makeMultiBowlingGame() {
        List<BowlingGameDto> bowlingGameDtos = multiBowlingGame.getBowlingGameDtos();

        List<String> pickedPlayerNames = bowlingGameDtos.stream()
                .map(BowlingGameDto::getPlayerName)
                .collect(Collectors.toList());

        assertThat(pickedPlayerNames).isEqualTo(playerNames);
    }

    @DisplayName("볼링 게임 최초 시작시, 1번 플레이어가 가장 먼저 플레이함")
    @Test
    public void currentPlayer_1번_최초() {
        assertThat(multiBowlingGame.getCurrentPlayerName()).isEqualTo("1번!");
    }

    @DisplayName("1번 플레이어가 아직 플레이할 투구가 남아 있다면, 계속 1번 플레이어가 턴을 가짐")
    @Test
    public void currentPlayer_1번_계속() {
        multiBowlingGame.bowl(PitchScore.valueOf(3));

        assertThat(multiBowlingGame.getCurrentPlayerName()).isEqualTo("1번!");
    }

    @DisplayName("1번 플레이어가 스트라이크로 현재 프레임의 투구를 완료하면 다음 턴은 2번 플레이어가 가짐")
    @Test
    public void currentPlayer_2번() {
        multiBowlingGame.bowl(PitchScore.valueOf(10));

        assertThat(multiBowlingGame.getCurrentPlayerName()).isEqualTo("2번!");
    }

    @DisplayName("모든 플레이어가 현재 프레임의 투구를 종료한 뒤, 다음 프레임으로 이동하면 다시 1번 플레이어가 턴을 잡음")
    @Test
    public void currentPlayer_프레임_종료_다시_1번() {
        multiBowlingGame.bowl(PitchScore.valueOf(10));
        multiBowlingGame.bowl(PitchScore.valueOf(10));
        multiBowlingGame.bowl(PitchScore.valueOf(10));

        multiBowlingGame.moveToNextFrame();

        assertThat(multiBowlingGame.getCurrentPlayerName()).isEqualTo("1번!");
    }

    @DisplayName("모든 플레이어가 10프레임까지 완료하면 nextTurn이 존재하지 앟는다")
    @Test
    public void hasNextTurn_False() {
        for (int i = 0; i < 12; i++) {
            multiBowlingGame.bowl(PitchScore.valueOf(10));
            multiBowlingGame.bowl(PitchScore.valueOf(10));
            multiBowlingGame.bowl(PitchScore.valueOf(10));
            multiBowlingGame.moveToNextFrame();
        }

        assertThat(multiBowlingGame.hasNextTurn()).isFalse();
    }

    @DisplayName("현재 플레이할 수 있는 Game Turn을 찾지 못하면 예외 발생")
    @Test
    public void cannotFindGameTurn() {
        multiBowlingGame.bowl(PitchScore.valueOf(10));
        multiBowlingGame.bowl(PitchScore.valueOf(10));
        multiBowlingGame.bowl(PitchScore.valueOf(10));

        assertThatThrownBy(() -> {
            multiBowlingGame.bowl(PitchScore.valueOf(10));
        }).isInstanceOf(BowlingBuildingException.class)
                .hasMessageContaining(BowlingBuildingException.CANNOT_FIND_GAME_TURN);
    }
}
