package qna.domain;

import qna.dto.DeleteHistoryDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DeleteHistorys {
    List<DeleteHistory> deleteHistories = new ArrayList<>();

    public void add(DeleteHistory deleteHistory) {
        deleteHistories.add(deleteHistory);
    }

    public void addTypeQuestion(DeleteHistoryDTO deleteHistoryDTO) {
        deleteHistories.add(new DeleteHistory(ContentType.QUESTION, deleteHistoryDTO))
    }

    public void addTypeAnswer(Answers answers) {
        for (Answer answer : answers) {
            answer.setDeleted(true);
            deleteHistories.add(new DeleteHistory(ContentType.ANSWER, new DeleteHistoryDTO(answer.getId(), answer.getWriter(), LocalDateTime.now()))));
        }
    }


}
