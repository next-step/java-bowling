package qna.domain;

import org.hibernate.annotations.Where;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
        this.answers = new ArrayList<>(answers);
    }

    public Answers add(Answer answer) {
        List<Answer> copies = new ArrayList<>(answers);
        copies.add(answer);
        return new Answers(copies);
    }

    public Answers deleteAnswers(User loginUser) {
        List<Answer> copies = answers.stream()
                .peek(answer -> answer.deleteAnswer(loginUser))
                .collect(Collectors.toList());
        return new Answers(copies);
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
