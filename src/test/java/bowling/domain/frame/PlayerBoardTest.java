package bowling.domain.frame;

import bowling.domain.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerBoardTest {
  @Test
  @DisplayName("현재 프레임이 준비 상태인지 체크")
  void currentFrameFinishedCheckTest() {
    Player player = new Player("zfz");
    PlayerBoard playerBoard = new PlayerBoard(player);

    playerBoard.addNewBall(10);
    assertThat(playerBoard.checkCurrentFrameReady()).isTrue();
  }

  @Test
  @DisplayName("프레임 진행 중일 때 true를 return한다")
  void currentFrameRunningTest() {
    Player player = new Player("zfz");
    PlayerBoard playerBoard = new PlayerBoard(player);

    playerBoard.addNewBall(5);
    assertThat(playerBoard.checkTargetRoundFinished(1)).isTrue();
  }

  @Test
  @DisplayName("하나의 프레임이 완료되어 넘어가면 false를 return한다")
  void currentFrameFinishedTest() {
    Player player = new Player("zfz");
    PlayerBoard playerBoard = new PlayerBoard(player);

    playerBoard.addNewBall(10);
    assertThat(playerBoard.checkTargetRoundFinished(1)).isFalse();
  }

  @Test
  @DisplayName("전체가 종료되면 false를 return한다")
  void finishedAllRoundTest() {
    Player player = new Player("zfz");
    PlayerBoard playerBoard = new PlayerBoard(player);

    playerBoard.firstFrame().bowl(10)
      .bowl(2).bowl(8)
      .bowl(7).bowl(1)
      .bowl(2).bowl(4)
      .bowl(3).bowl(6)
      .bowl(7).bowl(2)
      .bowl(7).bowl(1)
      .bowl(6).bowl(0)
      .bowl(10).bowl(1).bowl(5);

    assertThat(playerBoard.checkTargetRoundFinished(10)).isFalse();
  }
}