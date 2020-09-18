package bowling.domain.bowl.identity;

import bowling.domain.bowl.BowlResult;

import java.util.ArrayList;
import java.util.List;

public class BowlIdentityFinder {

    public static final List<BowlIdentity> BOWL_IDENTITIES = new ArrayList<>();

    static {
        BOWL_IDENTITIES.add(new NoneBowlIdentity());
        BOWL_IDENTITIES.add(new ProgressBowlIdentity());
        BOWL_IDENTITIES.add(new StrikeBowlIdentity());
        BOWL_IDENTITIES.add(new SpareBowlIdentity());
        BOWL_IDENTITIES.add(new MissBowlIdentity());
        BOWL_IDENTITIES.add(new GutterBowlIdentity());
    }

    private BowlIdentityFinder() {

    }

    public static BowlIdentity find(BowlResult bowlResult) {
        return BOWL_IDENTITIES.stream()
                .filter(bowlIdentity -> bowlIdentity.identity(bowlResult))
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }

}
