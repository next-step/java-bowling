package qna.domain;

import qna.CannotDeleteException;

import java.time.LocalDateTime;
import java.util.*;

public class Answers {
    private final List<Answer> answers;

    public Answers(List<Answer> answers){
        this.answers = answers;
    }

    public Deque<DeleteHistory> deletedAnswers(User loginUser) throws CannotDeleteException {
        Deque<DeleteHistory> deleteHistories = new LinkedList<>();

        for (Answer answer : answers) {
            DeleteHistory deleteHistory = answer.deleted(loginUser);
            deleteHistories.add(deleteHistory);
        }

        return deleteHistories;
    }
}
