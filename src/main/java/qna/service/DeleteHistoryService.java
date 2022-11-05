package qna.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import qna.domain.Answer;
import qna.domain.DeleteHistory;
import qna.domain.DeleteHistoryRepository;
import qna.domain.Question;

import javax.annotation.Resource;
import java.util.List;

@Service("deleteHistoryService")
public class DeleteHistoryService {
    @Resource(name = "deleteHistoryRepository")
    private DeleteHistoryRepository deleteHistoryRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveAll(List<DeleteHistory> deleteHistories) {
        deleteHistoryRepository.saveAll(deleteHistories);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveQuestion(Question question) {
        if (question.isNotDeleted()) {
            throw new IllegalArgumentException("질문은 삭제된 상태가 아닙니다.");
        }
        deleteHistoryRepository.save(question.deleteHistory());
    }

    public void saveAnswer(Answer answer) {
        if (answer.isNotDeleted()) {
            throw new IllegalArgumentException("질문은 삭제된 상태가 아닙니다.");
        }
        deleteHistoryRepository.save(answer.deleteHistory());
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void save(DeleteHistory deleteHistory) {
        deleteHistoryRepository.save(deleteHistory);
    }
}
