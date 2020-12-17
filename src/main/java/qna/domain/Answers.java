package qna.domain;

import qna.CannotDeleteException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Answers {
    private List<Answer> answers;

    private Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public static Answers of(Answer answer) {
        return new Answers(add(answer));
    }

    public static List<Answer> add(Answer answer) {
        List<Answer> answers = new ArrayList<>();
        answers.add(answer);
        return Collections.unmodifiableList(answers);
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void answerMatchOwner(User loginUser) throws CannotDeleteException {
        for (Answer answer : this.answers) {
            answer.isMatchOwner(loginUser);
        }
    }

    public DeleteHistory delete() {
        DeleteHistory deleteHistory = new DeleteHistory();
        for (Answer answer : this.answers) {
            deleteHistory = answer.delete();
        }
        return deleteHistory;
    }
}
