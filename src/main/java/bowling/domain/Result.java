package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class Result {
  private List<String> frameResults;

  public Result(){
    this.frameResults = new ArrayList<>();
  }

  public void add(String frameResult){
    frameResults.add(frameResult);
  }

  public List<String> frameResults(){
    return frameResults;
  }
}
