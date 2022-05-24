package qna.domain.answer;

import org.hibernate.annotations.Where;
import qna.domain.deleteHistory.DeleteHistories;
import qna.domain.deleteHistory.DeleteHistory;
import qna.domain.user.User;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Embeddable
public class Answers {

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    @Where(clause = "deleted = false")
    @OrderBy("id ASC")
    private final List<Answer> answers;

    public Answers() {
        this(new ArrayList<>());
    }

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public void add(Answer answer) {
        this.answers.add(answer);
    }

    public void delete(User writer) {
        if (this.answers.isEmpty()) {
            return;
        }

        answers.forEach(answer -> answer.delete(writer));
    }

    public DeleteHistories deleteHistories() {
        List<DeleteHistory> deleteHistories = this.answers
                .stream()
                .filter(Answer::isDeleted)
                .map(Answer::deleteHistory)
                .collect(Collectors.toList());
        return new DeleteHistories(deleteHistories);
    }
}
