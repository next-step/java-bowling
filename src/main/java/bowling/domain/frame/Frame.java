package bowling.domain.frame;

import bowling.domain.Score;
import bowling.domain.result.TotalResult;
import bowling.domain.turn.FallenPins;

public interface Frame {

  boolean checkReady();

  boolean checkFinished();

  String show();

  Frame bowl(FallenPins fallenPins);

  TotalResult showFullResult();

  void addResult(TotalResult totalResult);

  Score score();

  Score calculateAdditionalScore(Score score);

  int round();
}
