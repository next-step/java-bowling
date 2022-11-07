package qna.domain;

import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class DeletedHistoryListener {

    private final DeleteHistoryRepository deleteHistoryRepository;

    public DeletedHistoryListener(DeleteHistoryRepository deleteHistoryRepository) {
        this.deleteHistoryRepository = deleteHistoryRepository;
    }

    @TransactionalEventListener
    public void questionDeleteEventHandler(final QuestionDeleteEvent event){
        deleteHistoryRepository.save(event.getDeletedHistory());
    }

    @TransactionalEventListener
    public void answerDeleteEventHandler(final AnswerDeleteEvent event){
        deleteHistoryRepository.save(event.getDeletedHistory());
    }

}
