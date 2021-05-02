package bowling.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

  @Test
  @DisplayName("생성, 1라운드 테스트")
  void createTest(){
    Board board = new Board();
    board.makeFirstFrame();

    Assertions.assertThat(board.frames().size()).isEqualTo(1);
  }



  @Test
  @DisplayName("사용자를 추가할 수 있다.")
  void addPlayerTest(){
    Player player = new Player("PLS");
    Board board = new Board();

    board.addPlayer(player);
  }

}