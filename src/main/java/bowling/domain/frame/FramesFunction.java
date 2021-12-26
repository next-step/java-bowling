package bowling.domain.frame;

@FunctionalInterface
public interface FramesFunction<T, U, R> {


    R apply(T t, U u);

}
