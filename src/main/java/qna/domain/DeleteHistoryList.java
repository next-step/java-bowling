package qna.domain;

import qna.service.DeleteHistoryService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DeleteHistoryList {

    private DeleteHistoryService deleteHistoryService;

    private List<DeleteHistory> deleteHistories;

    public DeleteHistoryList(DeleteHistoryService deleteHistoryService) {
        this.deleteHistories = new ArrayList<>();
        this.deleteHistoryService = deleteHistoryService;
    }

    public void delete(Question question) {
        deleteQuestion(question);
        for (Answer answer : question.getAnswers()) {
            deleteAnswer(answer);
        }
        deleteHistoryService.saveAll(this.deleteHistories);
    }

    public void deleteQuestion(Question question) {
        question.delete();
        this.deleteHistories.add(new DeleteHistory(ContentType.QUESTION, question.getId(), question.getWriter(), LocalDateTime.now()));
    }

    public void deleteAnswer(Answer answer) {
        answer.delete();
        this.deleteHistories.add(new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter(), LocalDateTime.now()));
    }

}
