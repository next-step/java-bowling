package qna.domain;

import qna.service.DeleteHistoryService;

import java.util.ArrayList;
import java.util.List;

public class DeleteHistoryList {

    private DeleteHistoryService deleteHistoryService;

    private List<DeleteHistory> deleteHistories;

    public DeleteHistoryList(DeleteHistoryService deleteHistoryService) {
        this.deleteHistories = new ArrayList<>();
        this.deleteHistoryService = deleteHistoryService;
    }

    public List<DeleteHistory> delete(Question question, AnswerList answerList) {
        this.deleteHistories.add(question.delete());
        this.deleteHistories.addAll(answerList.delete());
        deleteHistoryService.saveAll(this.deleteHistories);
        return this.deleteHistories;
    }

}
