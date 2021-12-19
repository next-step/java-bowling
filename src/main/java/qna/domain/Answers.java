package qna.domain;

import org.hibernate.annotations.Where;
import qna.exception.CannotDeleteException;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static qna.exception.ConsumerExceptionWrapper.wrapper;

@Embeddable
public class Answers {
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    @Where(clause = "deleted = false")
    @OrderBy("id ASC")
    private List<Answer> answers;

    public Answers() {

    }

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void add(Answer answer) {
        answers.add(answer);
    }

    public List<DeleteHistory> delete(User loginUser) throws CannotDeleteException {
        validateDelete(loginUser);
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        answers.forEach(answer -> {
            answer.setDeleted(true);
            deleteHistories.add(new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter(), LocalDateTime.now()));
        });
        return deleteHistories;
    }

    public void validateDelete(User writer) throws CannotDeleteException {
        answers.forEach(wrapper(answer -> {
            if (!answer.isOwner(writer)) {
                throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
            }
        }));
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

    @Override
    public String toString() {
        return "Answers{" +
                "answers=" + answers +
                '}';
    }
}
