package qna.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import qna.service.DeleteHistoryService;

@Component
public class DeleteEventHandler {

    @Resource(name = "deleteHistoryService")
    private DeleteHistoryService deleteHistoryService;

    @EventListener
    public void saveHistory(DeleteQuestionEvent deleteQuestionEvent) {
        System.out.println("Subscribe deleteQuestion event!!!!!");
        final Question question = deleteQuestionEvent.getQuestion();

        List<DeleteHistory> deleteHistories = new ArrayList<>();
        deleteHistories.add(new DeleteHistory(ContentType.QUESTION, question.getId(), question.getWriter(), LocalDateTime.now()));

        for (Answer answer : question.getAnswers().getAnswers()) {
            deleteHistories.add(new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter(), LocalDateTime.now()));
        }
        deleteHistoryService.saveAll(deleteHistories);
    }
}
