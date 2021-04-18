package qna;

@FunctionalInterface
public interface FunctionWithCannotDeleteException<T, R, E extends CannotDeleteException> {
  R apply(T t) throws E;
}
