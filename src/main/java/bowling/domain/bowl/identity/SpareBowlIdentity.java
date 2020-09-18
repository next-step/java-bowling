package bowling.domain.bowl.identity;

import bowling.domain.bowl.Bowl;

import static bowling.domain.NumberOfPin.MAX_NUMBER_OF_PIN;

public class SpareBowlIdentity extends AbstractBowlIdentity {

    @Override
    public boolean identity(Bowl bowl) {
        return bowl.getBowlCount() == SECOND_BOWL &&
                bowl.getTotalNumberOfPin() == MAX_NUMBER_OF_PIN;
    }

}
