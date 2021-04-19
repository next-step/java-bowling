package qna.domain;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Embeddable
public class Answers extends AbstractEntity{

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private List<Answer> answers = new ArrayList<>();

    public Answers() {}

    private Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public void add(Answer answer) {
        this.answers.add(answer);
    }

    public void delete(User loginUser) {
        answers.forEach(answer -> answer.delete(loginUser));
    }

    public List<Answer> answers() {
        return Collections.unmodifiableList(answers);
    }
}
