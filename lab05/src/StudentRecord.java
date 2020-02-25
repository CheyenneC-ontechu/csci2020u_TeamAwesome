public class StudentRecord {

    private String studentID;
    private float midterm;
    private float assignments;
    private float finalExam;
    private float finalMark;
    private char letterGrade;

    StudentRecord(String studentID, float assignments, float midterm, float finalExam){
        this.studentID = studentID;
        this.assignments = assignments;
        this.midterm = midterm;
        this. finalExam = finalExam;
        setFinalMark();
        setLetterGrade();
    }
    private void setFinalMark(){
        this.finalMark = (float) (0.2*assignments + 0.3*midterm + 0.5*finalExam);
    }
    private void setLetterGrade(){
        if (finalMark >= 80){
            this.letterGrade = 'A';
        }
        else if (finalMark >=70) {
            this.letterGrade = 'B';
        }
        else if (finalMark >= 60) {
            this.letterGrade = 'C';
        }
        else if (finalMark >= 50) {
            this.letterGrade = 'D';
        }
        else {
            this.letterGrade = 'F';
        }
    }
    public String getStudentID(){return studentID;}
    public float getMidterm(){return midterm;}
    public float getAssignments(){return assignments;}
    public float getFinalExam(){return finalExam;}
    public float getFinalMark() {return finalMark;}
    public char getLetterGrade() {return letterGrade;}
}
