package bowling.domain.frame;

import bowling.domain.Player;
import bowling.domain.turn.FallenPins;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class PlayerBoardTest {
  @Test
  @DisplayName("현재 프레임이 준비 상태인지 체크")
  void currentFrameFinishedCheckTest(){
    Player player = new Player("zfz");
    PlayerBoard playerBoard = new PlayerBoard(player);

    playerBoard.addNewBall(new FallenPins(10));
    assertThat(playerBoard.checkCurrentFrameReady()).isTrue();
  }

  @Test
  @DisplayName("프레임 진행 중일 때 true를 return한다")
  void currentFrameRunningTest(){
    Player player = new Player("zfz");
    PlayerBoard playerBoard = new PlayerBoard(player);

    playerBoard.addNewBall(new FallenPins(5));
    assertThat(playerBoard.checkTargetRoundFinished(1)).isTrue();
  }

  @Test
  @DisplayName("하나의 프레임이 완료되어 넘어가면 false를 return한다")
  void currentFrameFinishedTest(){
    Player player = new Player("zfz");
    PlayerBoard playerBoard = new PlayerBoard(player);

    playerBoard.addNewBall(new FallenPins(10));
    assertThat(playerBoard.checkTargetRoundFinished(1)).isFalse();
  }

  @Test
  @DisplayName("전체가 종료되면 false를 return한다")
  void finishedAllRoundTest(){
    Player player = new Player("zfz");
    PlayerBoard playerBoard = new PlayerBoard(player);

    playerBoard.firstFrame().bowl(new FallenPins(10))
      .bowl(new FallenPins(2)).bowl(new FallenPins(8))
      .bowl(new FallenPins(7)).bowl(new FallenPins(1))
      .bowl(new FallenPins(2)).bowl(new FallenPins(4))
      .bowl(new FallenPins(3)).bowl(new FallenPins(6))
      .bowl(new FallenPins(7)).bowl(new FallenPins(2))
      .bowl(new FallenPins(7)).bowl(new FallenPins(1))
      .bowl(new FallenPins(6)).bowl(new FallenPins(0))
      .bowl(new FallenPins(10)).bowl(new FallenPins(1)).bowl(new FallenPins(5));

    assertThat(playerBoard.checkTargetRoundFinished(10)).isFalse();
  }
}