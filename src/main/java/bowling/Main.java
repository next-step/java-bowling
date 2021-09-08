package bowling;

import bowling.frame.FramesResult;
import bowling.frame.FramesResults;
import bowling.player.PlayerName;
import bowling.view.InsertView;
import bowling.view.ResultView;
import java.util.stream.IntStream;

public class Main {

  private static final int MAX_ROUND = 10;
  private static final int START_ROUND = 1;
  private static final int PLAYER_MIN_COUNT = 1;

  public static void main(String[] args) {

    FramesResults frameResults = new FramesResults();
    insertPlayer(frameResults);
    playMatch(frameResults);
  }

  private static void playMatch(final FramesResults frameResults) {

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

  private static void insertPlayer(final FramesResults frameResults) {
    IntStream.rangeClosed(PLAYER_MIN_COUNT, InsertView.playerCount())
        .mapToObj(i -> FramesResult.of(new PlayerName(InsertView.inputName(i))))
        .forEach(frameResults::addFrameResult);
  }

  private static void printScoreBoard(final FramesResults frameResults) {

    ResultView.printHeader();

    frameResults.getFramesResults()
        .stream()
        .map(FramesResult::getResult)
        .forEach(ResultView::printBoard);
  }
}