package qna.domain;

import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
@Embeddable
public class Answers extends AbstractEntity {

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    @Where(clause = "deleted = false")
    @OrderBy("id ASC")
    private List<Answer> answers;

    public Answers() {
        this.answers = new ArrayList<>();
    }

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public void add(Answer answer) {
        this.answers.add(answer);
    }

    public boolean allIsOwner(User loginUser) {
        Optional<Answer> answerNotByLoginUser = answers.stream()
                .filter(answer -> !answer.isOwner(loginUser))
                .findAny();

        return !answerNotByLoginUser.isPresent();
    }

    public void deleteAnswers(boolean b, List<DeleteHistory> deleteHistories) {
        this.answers.stream()
                .forEach(answer -> {
                    answer.setDeleted(b);
                    deleteHistories.add(new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter(), LocalDateTime.now()));
                });
    }
}
