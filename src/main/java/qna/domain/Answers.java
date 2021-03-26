package qna.domain;

import org.hibernate.annotations.Where;
import qna.CannotDeleteException;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<Answer> getAnswers() {
        return Collections.unmodifiableList(new ArrayList<>(answers));
    }

    public DeleteHistories deleteAllBy(User loginUser, LocalDateTime deleteDate) {
        validateAllAuthority(loginUser);
        return answers.stream()
                .map(answer -> answer.delete(deleteDate))
                .collect(Collectors.collectingAndThen(Collectors.toList(), DeleteHistories::new));
    }

    private void validateAllAuthority(User loginUser) {
        if (notOwnedAnswerExist(loginUser)) {
            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }
    }

    private boolean notOwnedAnswerExist(User loginUser) {
        return answers.stream().anyMatch(answer -> !answer.isOwner(loginUser));
    }

}
