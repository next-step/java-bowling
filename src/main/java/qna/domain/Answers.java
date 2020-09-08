package qna.domain;

import qna.CannotDeleteException;

import java.util.List;

public class Answers {

    List<Answer> answers;

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public static Answers create(List<Answer> answers){
        return new Answers(answers);
    }

    public DeleteHistories delete(User user, DeleteHistories deleteHistories) throws CannotDeleteException {
        for(Answer answer : answers) {
            answer.delete(user, deleteHistories);
        }

        return deleteHistories;
    }
}
