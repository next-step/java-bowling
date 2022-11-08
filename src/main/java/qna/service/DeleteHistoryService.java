package qna.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import qna.domain.DeleteHistory;
import qna.domain.DeleteHistoryRepository;
import qna.domain.Question;

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

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveSofeDelete(Question question) {
        List<DeleteHistory> deleteHistories = addAllByQuestion(question);
        saveAll(deleteHistories);
    }

    public List<DeleteHistory> addAllByQuestion(Question question) {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        deleteHistories.add(DeleteHistory.from(question));
        deleteHistories.addAll(question.getAnswers().stream()
            .map(DeleteHistory::from)
            .collect(Collectors.toList()));
        return deleteHistories;
    }
}