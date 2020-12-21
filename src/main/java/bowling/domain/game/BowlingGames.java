package bowling.domain.game;

import bowling.domain.point.Point;

import java.util.List;

public class BowlingGames {
    private final List<Bowling> bowlings;
    private final BowlingGamesCursor bowlingGamesCursor;

    private BowlingGames(List<Bowling> bowlings) {
        this.bowlings = bowlings;
        this.bowlingGamesCursor = BowlingGamesCursor.of();
    }

    public static BowlingGames of(List<Bowling> bowlings) {
        return new BowlingGames(bowlings);
    }

    public void pitch(Point pitchedPoint) {
        getBowling(bowlingGamesCursor.getPeopleCursor())
                .pitch(pitchedPoint);

        if (isFrameTurnOver()) {
            bowlingGamesCursor.increasePeopleCursor();
            isLastFrame();
        }

    }

    private void isLastFrame() {
        if (isLastPeopleCursor() && isLastPeopleFrameTurnOver()) {
            bowlingGamesCursor.resetPeopleCursor();
            bowlingGamesCursor.increaseFrameNumberCursor();
        }
    }

    private boolean isLastPeopleCursor() {
        return bowlingGamesCursor.getPeopleCursor() == getParticipationPeopleCount();
    }

    private boolean isFrameTurnOver() {
        return bowlings.get(bowlingGamesCursor.getPeopleCursor())
                .isFrameFinished(bowlingGamesCursor.getFrameNumberCursor());
    }

    private boolean isLastPeopleFrameTurnOver() {
        return bowlings.get(getParticipationPeopleCount() - 1)
                .isFrameFinished(bowlingGamesCursor.getFrameNumberCursor());
    }


    public Bowling getBowling(int index) {
        return bowlings.get(index);
    }

    public String getPlayerName() {
        return bowlings.get(bowlingGamesCursor.getPeopleCursor())
                .getPlayerName();
    }

    public boolean isBowlingGameEnd() {
        return bowlings.stream()
                .allMatch(Bowling::isGameFinished);
    }

    public int getParticipationPeopleCount() {
        return bowlings.size();
    }
}
