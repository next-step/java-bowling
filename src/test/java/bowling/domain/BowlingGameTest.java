package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BowlingGameTest {

    @Test
    @DisplayName("게임 종료 판단 함수 테스트 : 아직 끝나지 않은 경우")
    void isEndGameTest1() {
        BowlingGame game = new BowlingGame(new Player("OSC"));
        assertThat(game.isEndGame()).isFalse();
        game.doGame(RuleConfig.NUMBER_OF_PIN);
        assertThat(game.isEndGame()).isFalse();
    }

    @Test
    @DisplayName("게임 종료 판단 함수 테스트 : 보너스 게임 없이 끝난 경우")
    void isEndGameTest2() {
        BowlingGame game = new BowlingGame(new Player("OSC"));
        for (int index = 0; index < RuleConfig.NUMBER_OF_FRAME; index++) {
            game.doGame(RuleConfig.NUMBER_OF_PIN / 2 - 1);
            game.doGame(RuleConfig.NUMBER_OF_PIN / 2 - 1);
        }

        assertThat(game.isEndGame()).isTrue();
    }

    @Test
    @DisplayName("게임 종료 판단 함수 테스트 : 보너스 게임이 남은 경우")
    void isEndGameTest3() {
        BowlingGame game = new BowlingGame(new Player("OSC"));
        for (int index = 0; index < RuleConfig.NUMBER_OF_FRAME; index++) {
            game.doGame(RuleConfig.NUMBER_OF_PIN);
        }

        assertThat(game.isEndGame()).isFalse();
    }

    @Test
    @DisplayName("게임 종료 판단 함수 테스트 : 보너스 게임까지 끝난 경우")
    void isEndGameTest4() {
        BowlingGame game = new BowlingGame(new Player("OSC"));
        for (int index = 0; index < RuleConfig.NUMBER_OF_FRAME; index++) {
            game.doGame(RuleConfig.NUMBER_OF_PIN);
        }
        game.doGame(RuleConfig.NUMBER_OF_PIN);

        assertThat(game.isEndGame()).isTrue();
    }

    @Test
    @DisplayName("진행중인 프레임 라운드 조회 함수 테스트")
    void getFrameRoundTest() {
        BowlingGame game = new BowlingGame(new Player("OSC"));
        for (int index = 0; index < RuleConfig.NUMBER_OF_FRAME; index++) {
            assertThat(game.getFrameRound()).isEqualTo(index + 1);
            game.doGame(RuleConfig.NUMBER_OF_PIN);
        }
    }

}