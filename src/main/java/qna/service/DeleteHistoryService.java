package qna.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import qna.domain.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
    public void save(DeleteHistory deleteHistory) {
        deleteHistoryRepository.save(deleteHistory);
    }

    @Transactional
    public void save(final Question question) {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        deleteHistories.add(new DeleteHistory(ContentType.QUESTION, question.getId(), question.getWriter(), LocalDateTime.now()));
        for (Answer answer : question.getAnswers().getAnswers()) {
            deleteHistories.add(new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter(), LocalDateTime.now()));
        }
        deleteHistoryRepository.saveAll(deleteHistories);
    }
}