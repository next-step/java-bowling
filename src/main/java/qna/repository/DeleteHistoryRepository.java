package qna.repository;

import org.springframework.data.repository.CrudRepository;
import qna.domain.DeleteHistory;

public interface DeleteHistoryRepository extends CrudRepository<DeleteHistory, Long> {

}
