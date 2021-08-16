package qna.domain.service;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import qna.domain.entity.DeleteHistory;
import qna.domain.repository.DeleteHistoryRepository;

@Service("deleteHistoryService")
public class DeleteHistoryService {

    @Resource(name = "deleteHistoryRepository")
    private DeleteHistoryRepository deleteHistoryRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveAll(final List<DeleteHistory> deleteHistories) {
        deleteHistoryRepository.saveAll(deleteHistories);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void save(final DeleteHistory deleteHistory) {
        deleteHistoryRepository.save(deleteHistory);
    }
}
