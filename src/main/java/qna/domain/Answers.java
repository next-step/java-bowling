package qna.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import org.hibernate.annotations.Where;

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

    public void delete(User loginUser) {
        List<DeleteHistory> deleteHistories = answers.stream()
                .map(answer -> answer.delete(loginUser))
                .collect(Collectors.toList());
    }

    public List<Answer> getAnswers() {
        return answers;
    }
}
