package qna.domain;

import org.hibernate.annotations.Where;

import javax.persistence.CascadeType;
<<<<<<< HEAD
=======
import javax.persistence.Entity;
>>>>>>> cb44143bd22b16010852420b4bf07972ed45c0c6
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import java.util.ArrayList;
import java.util.List;
<<<<<<< HEAD
import java.util.Objects;
import java.util.stream.Collectors;

public class Answers {
=======

@Entity
public class Answers extends AbstractEntity {

>>>>>>> cb44143bd22b16010852420b4bf07972ed45c0c6
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    @Where(clause = "deleted = false")
    @OrderBy("id ASC")
    private List<Answer> answers;

<<<<<<< HEAD
    public Answers() {
        new ArrayList<Answer>();
    }
=======
    public Answers () {
        this.answers = new ArrayList<>();
    }

>>>>>>> cb44143bd22b16010852420b4bf07972ed45c0c6
    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

<<<<<<< HEAD
    public boolean isEmpty() {
        return answers == null || answers.isEmpty() ? true : false;
    }

    public void add(Answer answer) {
        //if (!answers.isEmpty()) {
            answers.add(answer);
        }
    //}

    public boolean haveSameWriter(User loginUser) {
        if (!answers.isEmpty()) {
            return answers.stream()
                    .allMatch(answer -> answer.getWriter() == loginUser);
        }
        return true;
    }


    public void deleted(boolean bool) {
        if (!answers.isEmpty()) {
            answers.forEach(answer -> answer.deleted(true));
        }
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
=======
    public void addAnswer(Answer answer) {
        if (answer != null) {
            answers.add(answer);
        }
>>>>>>> cb44143bd22b16010852420b4bf07972ed45c0c6
    }
}
