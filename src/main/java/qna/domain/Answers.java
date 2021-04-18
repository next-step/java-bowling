package qna.domain;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import qna.CannotDeleteException;
import qna.FunctionWithCannotDeleteException;

public class Answers {

  private final List<Answer> answers;

  public Answers(List<Answer> answers) {
    this.answers = answers;
  }

  public List<DeleteHistory> delete(User owner) throws CannotDeleteException {
    return answers.stream().map(wrapper(answer -> answer.delete(owner)))
        .collect(Collectors.toList());
  }

  private <T, R, E extends CannotDeleteException> Function<T, R> wrapper(FunctionWithCannotDeleteException<T, R, E> fe) {
    return arg -> {
      try {
        return fe.apply(arg);
      } catch (CannotDeleteException e) {
        throw new CannotDeleteException(e.getMessage());
      }
    };
  }

  public List<Answer> getList() {
    return Collections.unmodifiableList(answers);
  }

  public Answer getAnswer(int index) {
    return answers.get(index);
  }


}
