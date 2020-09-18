package bowling.domain.bowl.identity;

import bowling.domain.bowl.Bowl;

import static bowling.domain.NumberOfPin.MIN_NUMBER_OF_PIN;

public class GutterBowlIdentity extends AbstractBowlIdentity {

    @Override
    public boolean identity(Bowl bowl) {
        return bowl.getBowlCount() == SECOND_BOWL &&
                bowl.getTotalNumberOfPin() == MIN_NUMBER_OF_PIN;
    }

}
