package qna.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import qna.domain.DeleteHistory;
import qna.domain.DeleteHistoryRepository;

import java.util.List;

@Service("deleteHistoryService")
public class DeleteHistoryService {
    private final DeleteHistoryRepository deleteHistoryRepository;

    public DeleteHistoryService(DeleteHistoryRepository deleteHistoryRepository) {
        this.deleteHistoryRepository = deleteHistoryRepository;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveAll(List<DeleteHistory> deleteHistories) {
        deleteHistoryRepository.saveAll(deleteHistories);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void save(DeleteHistory deleteHistory) {
        deleteHistoryRepository.save(deleteHistory);
    }
}
