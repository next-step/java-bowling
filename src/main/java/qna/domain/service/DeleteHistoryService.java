package qna.domain.service;

import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import qna.domain.entity.Answer;
import qna.domain.entity.DeleteHistory;
import qna.domain.entity.Question;
import qna.domain.repository.DeleteHistoryRepository;

@Service("deleteHistoryService")
public class DeleteHistoryService {

    @Resource(name = "deleteHistoryRepository")
    private DeleteHistoryRepository deleteHistoryRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void deleteQuestion(final Question question) {
        deleteAnswers(question.getAnswers());

        save(DeleteHistory.fromQuestion(question));
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void deleteAnswers(final List<Answer> answers) {
        saveAll(answers.stream()
            .map(DeleteHistory::fromAnswer)
            .collect(Collectors.toList()));
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveAll(final List<DeleteHistory> deleteHistories) {
        deleteHistoryRepository.saveAll(deleteHistories);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void save(final DeleteHistory deleteHistory) {
        deleteHistoryRepository.save(deleteHistory);
    }
}
