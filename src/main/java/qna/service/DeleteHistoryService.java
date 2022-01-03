package qna.service;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import qna.domain.DeleteHistory;
import qna.domain.DeleteHistoryRepository;

@Service("deleteHistoryService")
public class DeleteHistoryService {

	@Resource(name = "deleteHistoryRepository")
	private DeleteHistoryRepository deleteHistoryRepository;

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void saveAll(List<DeleteHistory> deleteHistories) {
		deleteHistoryRepository.saveAll(deleteHistories);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void save(DeleteHistory deleteHistory) {
		deleteHistoryRepository.save(deleteHistory);
	}
}