package ManagementSystem;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.*;

@Getter
@Setter
@ToString
public
class StudentModel implements Serializable, Comparable<StudentModel> {
    private String studentID;
    private String studentName;
    private String studentAddress;
    private int studentGradeOne;
    private int studentGradeTwo;
    private int studentGradeDemo;
    private int studentGradeTotal;
    private double studentGradeAvg;
    private String studentStatus;
    private StudentView studentView;

    public StudentModel(String studentID, String studentName, int studentGradeOne, int studentGradeTwo, int studentGradeDemo) {
        try {
            this.studentName = studentName.substring(0, 1).toUpperCase() + studentName.substring(1);
            this.studentID = studentID.toUpperCase();
            this.studentGradeOne = studentGradeOne;
            this.studentGradeTwo = studentGradeTwo;
            this.studentGradeDemo = studentGradeDemo;
            this.studentGradeTotal = studentGradeOne + studentGradeTwo + studentGradeDemo;
            try{
            this.studentGradeAvg = studentGradeTotal / 3;
            }catch (ArithmeticException e){
                System.out.println("Error: " + e.getMessage());
            }
            this.studentStatus = studentStatusDefine();
        }catch (IllegalArgumentException e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    public int compareTo(StudentModel studentModel) {
        if (getStudentGradeTotal() < studentModel.getStudentGradeTotal()) return -1;
        else if (getStudentGradeTotal() == studentModel.getStudentGradeTotal()) return 0;
        else return 1;
    }
    public String studentStatusDefine() {
        if (getStudentGradeAvg() < 6) return "Fail";
        else if (getStudentGradeAvg() == 6) return "Pass";
        else if (getStudentGradeAvg() == 7 || getStudentGradeAvg() == 8) return "Merit";
        else return "Distinction";
    }
}
