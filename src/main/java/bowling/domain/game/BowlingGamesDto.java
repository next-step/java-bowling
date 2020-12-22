package bowling.domain.game;

import java.util.Collections;
import java.util.List;

public class BowlingGamesDto {
    private final List<Bowling> bowlings;

    public BowlingGamesDto(List<Bowling> bowlings) {
        this.bowlings = bowlings;
    }



    public int getParticipationPeopleCount() {
        return bowlings.size();
    }

    public Bowling getBowling(int index) {
        return bowlings.get(index);
    }
}
