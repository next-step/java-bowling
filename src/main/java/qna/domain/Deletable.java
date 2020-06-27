package qna.domain;

import java.util.List;

public interface Deletable {

  void delete(User login);

  List<DeleteHistory> getDeleteHistories();
}
