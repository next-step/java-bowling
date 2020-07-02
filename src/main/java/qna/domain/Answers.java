package qna.domain;

import qna.CannotDeleteException;

import java.util.ArrayList;
import java.util.List;

public class Answers {

    private final List<Answer> answers;

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public List<DeleteHistory> delete(User loginUser) throws CannotDeleteException {
        List<DeleteHistory> list = new ArrayList<>();
        for (Answer answer : answers) {
            DeleteHistory delete = answer.delete(loginUser);
            list.add(delete);
        }
        return list;
    }
}
