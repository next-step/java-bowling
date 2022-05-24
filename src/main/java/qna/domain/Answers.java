package qna.domain;

import qna.CannotDeleteException;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Answers {
    private final List<Answer> answers ;

    public  Answers() {
        this.answers = new ArrayList<>();
    }
    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public void add(Answer answer) {
        this.answers.add(answer);
    }

    public void checkPrivilegeOnAnswer(User loginUser) throws CannotDeleteException {
        for (Answer answer : this.answers) {
            if (!answer.isOwner(loginUser)) {
                throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
            }
        }
    }

    public DeleteHistories deleteHistory() {
        DeleteHistories deleteHistories = new DeleteHistories();
        for (Answer answer : this.answers) {
            deleteHistories.add(answer.deleteHistory());
        }
        return deleteHistories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Answers answers1 = (Answers) o;
        return Objects.equals(answers, answers1.answers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(answers);
    }
}
