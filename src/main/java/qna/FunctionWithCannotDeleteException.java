package qna;

@FunctionalInterface
public interface FunctionWithCannotDeleteException<T, E extends CannotDeleteException> {
  void apply(T t) throws E;
}
