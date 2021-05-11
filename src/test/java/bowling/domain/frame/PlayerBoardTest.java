package bowling.domain.frame;

import bowling.domain.Player;
import bowling.domain.turn.FallenPins;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class PlayerBoardTest {
  @Test
  @DisplayName("현재 프레임이 종료되었는지 체크")
  void currentFrameFinishedCheckTest(){
    Player player = new Player("zfz");
    PlayerBoard playerBoard = new PlayerBoard(player);

    playerBoard.addNewBall(new FallenPins(10));
    assertThat(playerBoard.checkCurrentFrameFinished()).isTrue();
  }
}