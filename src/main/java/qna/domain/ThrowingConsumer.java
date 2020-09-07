package qna.domain;

import java.util.function.Consumer;

@FunctionalInterface
public interface ThrowingConsumer<T, E extends Exception> {
  void apply(T t) throws E;

  static <T, E extends Exception> Consumer<T> wrapper(ThrowingConsumer<T, E> throwingConsumer) {
    return i -> {
      try {
        throwingConsumer.apply(i);
      } catch (Exception ex) {
        throw new RuntimeException(ex);
      }
    };
  }
}
