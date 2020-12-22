package bowling.domain.game;

import bowling.domain.point.Point;

import java.util.Collections;
import java.util.List;

public class BowlingGames {
    private final List<Bowling> bowlings;

    private BowlingGames(List<Bowling> bowlings) {
        this.bowlings = bowlings;
    }

    public static BowlingGames of(List<Bowling> bowlings) {
        return new BowlingGames(bowlings);
    }

    public void pitch(int peopleCursor, Point pitchedPoint) {
        getBowling(peopleCursor)
                .pitch(pitchedPoint);

    }

    private Bowling getBowling(int index) {
        return bowlings.get(index);
    }

    private boolean isLastPeopleFrameTurnOver(int frameCursor) {
        return bowlings.get(getParticipationPeopleCount() - 1)
                .isFrameFinished(frameCursor);
    }


    public String getPlayerName(int peopleCursor) {
        return bowlings.get(peopleCursor)
                .getPlayerName();
    }

    public boolean isBowlingGameEnd() {
        return bowlings.stream()
                .allMatch(Bowling::isGameFinished);
    }

    public int getParticipationPeopleCount() {
        return bowlings.size();
    }

    public BowlingGamesDto getBowlingDto() {
        return new BowlingGamesDto(Collections.unmodifiableList(bowlings));
    }

    public boolean isLastPeople(int peopleCursor, int frameCursor) {
        return bowlings.get(peopleCursor)
                .isFrameFinished(frameCursor);
    }

    public boolean isLastFramePeople(int frameCursor) {
        return isLastPeopleFrameTurnOver(frameCursor);
    }
}
