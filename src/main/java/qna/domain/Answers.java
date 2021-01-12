package qna.domain;

import qna.CannotDeleteException;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class Answers {

    private List<Answer> answers;

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public void of(List<Answer> answers) {
        this.answers = answers;
    }

    //answer들 삭제 가능여부 체크
    public void checkAllDelible(User loginUser) throws CannotDeleteException {
        for (Answer answer : answers) {
            answer.checkDelible(loginUser);
        }
    }

    //answer 전체 삭제
    public void delete() {
        for (Answer answer : answers) {
            answer.setDeleted(true);
        }
    }

    public List<Answer> getAnswers() {
        return Collections.unmodifiableList(answers);
    }
}
