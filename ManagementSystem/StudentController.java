package ManagementSystem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

public class StudentController implements ActionListener {
    private StudentView studentView;

    public StudentController(StudentView studentView) {
        this.studentView = studentView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals("Enter")) {  //Input
            try {
                if (!StudentView.isValidateStudentCodeContain(this.studentView.textStudentCode.getText())) {
                    JOptionPane.showMessageDialog(null, "Student Code is not valid, you need to contain GCD");
                } else if (!StudentView.isValidateStudentCodeSize(this.studentView.textStudentCode.getText())) {
                    JOptionPane.showMessageDialog(null, "Student Code need to be 9 digits, no more no less");
                } else if (!StudentView.isValidateStudentCodeSame(this.studentView.textStudentCode.getText())) {
                    JOptionPane.showMessageDialog(null, "Student Code is already exist");
                } else if (!StudentView.isValidateGrade(this.studentView.textStudentGradeOne.getText())) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid grade two");
                } else if (!StudentView.isValidateGrade(this.studentView.textStudentGradeTwo.getText())) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid grade two");
                } else if (!StudentView.isValidateGrade(this.studentView.textStudentGradeDemo.getText())) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid grade demo");
                } else if (!StudentView.isValidateStudentFullField()) {
                    JOptionPane.showMessageDialog(null, "Please enter all information");
                } else if (!StudentView.isValidateStudentisCharacter(this.studentView.textStudentName.getText())) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid name (not contain number)");
                } else if (!StudentView.isValidateStudentNameLength(this.studentView.textStudentName.getText())) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid name length (10 - 30)");
                } else {
                    JOptionPane.showConfirmDialog(studentView, JOptionPane.INFORMATION_MESSAGE, "Successfully entered information", JOptionPane.DEFAULT_OPTION);
                    studentView.studentList.addStudentIntoTable();
                    for (int i = 0; i < studentView.studentTextFieldGroup.length; i++) {
                        studentView.studentTextFieldGroup[i].setText("");
                    }
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter a number in the grade fields");
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        } else if (command.equals("Output")) {  //Output
            studentView.model = studentView.studentList.displayStudentIntoTable(studentView.model);
        } else if (command.equals("Search")) {  //Search
            try {
                String answer = JOptionPane.showInputDialog(this, "Please enter the student ID");
                if (answer != null) {
                    studentView.model = studentView.studentList.searchStudentInTheTable(answer, studentView.model);
                }
            } catch (Exception ex) {
                JOptionPane.showConfirmDialog(null, "Please enter a valid student code in the table");
            }
        } else if (command.equals("Sort")) {  //Sort
            studentView.model = studentView.studentList.Descending(studentView.model);
        } else if (command.equals("Delete")) {  //Delete
            try {
                String answer = JOptionPane.showInputDialog(this, "Please enter the student ID");
                if (answer != null) {
                    studentView.model = studentView.studentList.deleteStudentInTheTable(answer, StudentView.model, "saveDirectory.txt");
                }
            } catch (Exception ex) {
                JOptionPane.showConfirmDialog(null, "Please enter a valid student code in the table");
            }
        } else if (command.equals("Save")) {  //Save
            studentView.studentList.saveStudentInTheTable();
        } else if (command.equals("Open")) {  //Open txt
            studentView.model = studentView.studentList.loadStudentInTheTable(studentView.model);
        } else if (command.equals("Refresh")) {
            studentView.refreshStudent();
        } else if (command.equals("Update")) {  //Update
            String answer = JOptionPane.showInputDialog(this, "Please enter the student ID");
            if (answer != null) {
                studentView.model = studentView.studentList.updateStudentInTheTable(answer, studentView.model);
            }
        }else if(command.equals("Exit")){
            System.exit(0);
        }
    }
}
