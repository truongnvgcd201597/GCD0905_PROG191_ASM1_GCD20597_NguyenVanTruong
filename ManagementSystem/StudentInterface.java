package ManagementSystem;

import javax.swing.table.DefaultTableModel;

interface StudentInterface {
    //	Enter student
    public void addStudentIntoTable();
    public DefaultTableModel displayStudentIntoTable(DefaultTableModel model); //Output
    public DefaultTableModel searchStudentInTheTable(String studentCode, DefaultTableModel searchModel);
    public DefaultTableModel Descending(DefaultTableModel model); //Sort
    //create method that delete student record in table and file data
    public DefaultTableModel deleteStudentInTheTable(String studentCode, DefaultTableModel model, String path);
    public void saveStudentInTheTable(); //Save
    public DefaultTableModel loadStudentInTheTable(DefaultTableModel model); //Load
    public DefaultTableModel updateStudentInTheTable(String studentCode, DefaultTableModel model); //Update
}
