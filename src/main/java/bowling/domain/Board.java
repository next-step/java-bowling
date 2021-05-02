package bowling.domain;

import bowling.domain.frame.Frame;
import bowling.domain.frame.Round;

import java.util.ArrayList;
import java.util.List;

public class Board {
  private static final int FIRST_ROUND = 1;
  private static final int ROUND_ADDING_NUMBER = 1;

  private final List<Round> rounds;
  private int roundNumber;

  public Board() {
    rounds = new ArrayList<>();
    roundNumber = FIRST_ROUND;
  }

  public void makeFirstFrame(Round round) {
    round.add(Frame.of(roundNumber));
  }

  public void addingFrame() {
    roundNumber += ROUND_ADDING_NUMBER;
    rounds.stream().forEach(round -> round.add(round.tail().makeNextRound()));
  }

  public int size() {
    return rounds.size();
  }

  public List<Round> rounds() {
    return rounds;
  }

  public int runningFrame() {
    return roundNumber;
  }

  public void addRound(Player player) {
    Round round = new Round(player);
    makeFirstFrame(round);
    rounds.add(round);
  }

  public boolean checkCurrentFrameDone() {
    long frameFinishedRounds = rounds.stream().filter(round -> round.checkCurrentRoundFinished()).count();
    long roundsSize = size();
    return frameFinishedRounds == roundsSize;
  }

  public boolean checkFinished() {
    long finishedCount = rounds.stream()
      .filter(round -> round.checkFinished())
      .count();
    long fullSize = rounds.size();
    return finishedCount == fullSize;
  }
}
