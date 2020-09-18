package bowling.domain.bowl.identity;

import bowling.domain.bowl.Bowl;

@FunctionalInterface
public interface BowlIdentity {

    boolean identity(Bowl bowl);

}
