package exercise1;
import java.util.HashMap;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Set;
import java.util.stream.*;
import java.util.*;

/**
 * Represents a student.
 * A Student is identified by its registration number.
 * A student gets scored in different courses.
 * These scores are expressed as integers on a scale from 0 to 20.
 */
public class Student {
    /**
     * Creates a new Student.
     *
     *
     * @throws NullPointerException if one of the parameter is null.
     */
    // name : String, registrationNumber : String (énoncé)
    private String name;
    private String registrationNumber;
    //HashMap <Integer , Animal > zoo = new HashMap <Integer , Animal >();
    HashMap <String,Integer> scorebycourse=new HashMap ();
    public Student(String name, String registrationNumber) {
        this.name=name;
        this.registrationNumber=registrationNumber;
    }

    /**
     * Sets the score of this student for the given course.
     * If the score is set twice for the same course, the new score replaces the previous one.
     *
     * @throws NullPointerException if the course name is null.
     * @throws IllegalArgumentException if the score is less than 0 or greater than 20.
     */
    //setScore(course : String, score : int) : void (énoncé)
    public void setScore(String course, Integer score) {
        //zoo.put(2,new Herbivore("Lapin", 2));

        scorebycourse.put(course,score);

    }

    /**
     * Returns the score for the given course.
     *
     * @return the score if found, <code>OptionalInt#empty()</code> otherwise.
     * @Override
     * public int hashCode()
      {
     return Objects.hash(firstName,lastName);
    }
     */
    public OptionalInt getScore(String course) {

        Integer nullableScore=scorebycourse.get(course);

        return nullableScore !=null? OptionalInt.of(nullableScore):OptionalInt.empty();
    }

    /**
     * Returns the average score.
     *
     @return the average score or 0 if there is none.
     */


    public double averageScore() {
        int count =0;
        double moyenne_course=0.0;
        if(scorebycourse.size()==0){
            return 0;
        }


            for (Integer score :scorebycourse.values()) {
                count ++;

                moyenne_course +=score ;


            }

            return moyenne_course/count;
            /*
            return scorebycourse.values().Stream
            .MapToInt(Integer::intValue)
            .average()
            .orElse(0.0);
             */
        }


    /**
     * Returns the course with the highest score.
     *
     * @return the best scored course or <code>Optional#empty()</code> if there is none.
     */
    public Optional<String> bestCourse() {

        return scorebycourse.entrySet().stream()
            .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
            .map(Map.Entry::getKey)
            .findFirst();
    }


    /**
     * Returns the highest score.
     *
     * @return the highest score or 0 if there is none.
     */
    public int bestScore() {
        int meilleur_score=0;
        if(scorebycourse.size()==0){
            return 0;
        }
        for (Integer score :scorebycourse.values()) {
            if(meilleur_score<score){
                meilleur_score=score;
            }
        }
        return meilleur_score;
    }

    /**
     * Returns the set of failed courses sorted by decreasing score.
     * A course is considered as passed if its score is higher than 12.
     */
    public Set<String> failedCourses() {
        /*
        List<Map.Entry<String, Integer>> filteredEntries = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : scorebycourse.entrySet()) {
            if (entry.getValue() < 12) {
                filteredEntries.add(entry);
            }
        }
        return null;
        */
        return scorebycourse.entrySet().stream()
            .filter(entry->entry.getValue()<12)
            .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
            .map(Map.Entry::getKey)
            .collect(Collectors.toCollection(LinkedHashSet::new));
    }
    /**
     * Returns <code>true</code> if the student has an average score greater than or equal to 12.0 and has less than 3 failed courses.
     */
    public boolean isSuccessful() {
       if(averageScore()>=12 && failedCourses().size()<3){
           return true;
       }
       else{return false;}
    }

    /**
     * Returns the set of courses for which the student has received a score, sorted by course name.
     */
    public Set<String> attendedCourses() {
        return null;
    }

    public String getName() {
        return name;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(getName());
        sb.append(" (").append(getRegistrationNumber()).append(")");
        return sb.toString();
    }
}
