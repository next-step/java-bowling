package bowling.domain.bowl.identity;

import bowling.domain.bowl.Bowl;

public class NoneBowlIdentity extends AbstractBowlIdentity {

    @Override
    public boolean identity(Bowl bowl) {
        return bowl.isNone();
    }

}
