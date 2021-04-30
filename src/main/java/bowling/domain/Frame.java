package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class Frame {
  private List<Ball> balls;

  private int addingScore;

  public Frame(){
    balls = new ArrayList<>();
  }

  public void shot(Ball ball){
    balls.add(ball);
  }

}
