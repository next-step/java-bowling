package bowling.domain.bowl.type;


@FunctionalInterface
public interface Condition {
    boolean check(BowlTypeDto bowlTypeDto);
}
