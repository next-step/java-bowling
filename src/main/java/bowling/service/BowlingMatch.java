package bowling.service;

import bowling.frame.FramesResult;
import bowling.frame.FramesResults;
import bowling.view.InsertView;
import bowling.view.ResultView;

public class BowlingMatch {

  private static final int MAX_ROUND = 10;
  private static final int START_ROUND = 1;


  public static void playMatch(final FramesResults frameResults) {

    printScoreBoard(frameResults);

    int round = START_ROUND;

    while (!frameResults.isEnd()) {
      pitchByPlayer(frameResults, round);
      round++;
    }
  }

  private static void pitchByPlayer(final FramesResults frameResults, final int round) {
    frameResults.getFramesResults()
        .forEach(framesResult -> {
          pitchForBowling(frameResults, framesResult);
          checkNextPitchForPlayer(frameResults, framesResult, round);
        });
  }

  private static void checkNextPitchForPlayer(final FramesResults frameResults,
      final FramesResult framesResult, final int round) {

    nextPitchByPlayer(frameResults, framesResult);
    thirdPitchByPlayer(frameResults, framesResult, round);
  }

  private static void thirdPitchByPlayer(final FramesResults frameResults,
      final FramesResult framesResult,
      final int round) {
    if (round == MAX_ROUND && !framesResult.isEndOfGame()) {
      pitchForBowling(frameResults, framesResult);
    }
  }

  private static void nextPitchByPlayer(final FramesResults frameResults,
      final FramesResult framesResult) {
    if (!framesResult.isFrameEnd()) {
      pitchForBowling(frameResults, framesResult);
    }
  }

  private static void pitchForBowling(final FramesResults frameResults,
      final FramesResult framesResult) {

    framesResult.playing(InsertView.throwBall(framesResult.getResult().getName()));
    printScoreBoard(frameResults);
  }


  private static void printScoreBoard(final FramesResults frameResults) {

    ResultView.printHeader();

    frameResults.getFramesResults()
        .stream()
        .map(FramesResult::getResult)
        .forEach(ResultView::printBoard);
  }
}
