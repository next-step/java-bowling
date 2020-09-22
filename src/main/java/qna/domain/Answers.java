package qna.domain;

import org.hibernate.annotations.Where;
import qna.CannotDeleteException;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Embeddable
public class Answers {

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    @Where(clause = "deleted = false")
    @OrderBy("id ASC")
    private List<Answer> answers = new ArrayList<>();

    public Answers() {
    }

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public void add(Answer answer) {
        answers.add(answer);
    }

    public void deleteAnswers(User loginUser) {
        Optional<Answer> maybeAnswer = answers.stream()
                .filter(answer -> !answer.isOwner(loginUser))
                .findAny();
        if (maybeAnswer.isPresent()) {
            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }
        setAllDeleted();
    }

    private void setAllDeleted() {
        answers.stream()
                .forEach(answer -> answer.setDeleted(true));
    }

    public void addToDeleteHistory(DeleteHistories deleteHistories) {
        deleteHistories.add(answers);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Answers answers1 = (Answers) o;
        return answers.equals(answers1.answers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(answers);
    }
}
