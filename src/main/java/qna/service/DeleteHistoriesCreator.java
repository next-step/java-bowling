package qna.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import qna.domain.ContentType;
import qna.domain.DeleteHistory;
import qna.domain.Question;

public class DeleteHistoriesCreator {
    public static List<DeleteHistory> create(Question question) {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        deleteHistories.add(new DeleteHistory(ContentType.QUESTION, question.getId(), question.getWriter(), LocalDateTime.now()));
        question.getAnswers().addDeleteHistory(deleteHistories);
        return deleteHistories;
    }
}
