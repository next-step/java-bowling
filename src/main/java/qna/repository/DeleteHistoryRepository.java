package qna.repository;

import org.springframework.data.repository.CrudRepository;
import qna.domain.entity.DeleteHistory;

public interface DeleteHistoryRepository extends CrudRepository<DeleteHistory, Long> {

}
