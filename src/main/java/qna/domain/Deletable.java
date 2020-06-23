package qna.domain;

import java.util.List;
import qna.CannotDeleteException;

public interface Deletable {

  void delete(User login) throws CannotDeleteException;

  List<DeleteHistory> getDeleteHistories();
}
