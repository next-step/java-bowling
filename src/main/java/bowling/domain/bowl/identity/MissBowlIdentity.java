package bowling.domain.bowl.identity;

import bowling.domain.bowl.Bowl;

import java.text.MessageFormat;

import static bowling.domain.NumberOfPin.MAX_NUMBER_OF_PIN;
import static bowling.domain.NumberOfPin.MIN_NUMBER_OF_PIN;

public class MissBowlIdentity extends AbstractBowlIdentity {

    @Override
    public boolean identity(Bowl bowl) {
        return bowl.getBowlCount() == SECOND_BOWL &&
                (bowl.getTotalNumberOfPin() > MIN_NUMBER_OF_PIN && bowl.getTotalNumberOfPin() < MAX_NUMBER_OF_PIN);
    }

}
