package ManagementSystem;

import java.nio.file.DirectoryNotEmptyException;
import java.util.Vector;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.io.*;


public class StudentList implements StudentInterface {
    private StudentModel studentModel;
    private StudentView studentView;
    private ArrayList<StudentModel> studentModelArrayList;  //ArrayList
    private ArrayList<StudentModel> studentStoredList;  //ArrayList

    public StudentList(StudentView studentView) {
        this.studentView = studentView;
        studentModelArrayList = new ArrayList<StudentModel>();
        studentStoredList = new ArrayList<StudentModel>();
    }

    @Override
    public void addStudentIntoTable() {
        studentModel = new StudentModel(studentView.getStudentCode(), studentView.getStudentName(), studentView.getGradeOne(),
                studentView.getGradeTwo(), studentView.getGradeDemo());
        studentModelArrayList.add(studentModel);
        studentStoredList.add(studentModel);
    }

    @Override
    public DefaultTableModel displayStudentIntoTable(DefaultTableModel model) {
        if (studentModelArrayList.size() != 0) {
            for (StudentModel entity : studentModelArrayList) {
                Vector<Object> addbyObj = new Vector<Object>();
                addbyObj.add(entity.getStudentID());
                addbyObj.add(entity.getStudentName());
                addbyObj.add(entity.getStudentGradeOne());
                addbyObj.add(entity.getStudentGradeTwo());
                addbyObj.add(entity.getStudentGradeDemo());
                addbyObj.add(entity.getStudentGradeTotal());
                addbyObj.add(entity.getStudentGradeAvg());
                addbyObj.add(entity.getStudentStatus());
                model.addRow(addbyObj);
            }
        } else {
            while (model.getRowCount() != 0) {
                model.removeRow(0);
            }
            for (StudentModel entity : studentStoredList) {
                Vector<Object> addbyObj = new Vector<Object>();
                addbyObj.add(entity.getStudentID());
                addbyObj.add(entity.getStudentName());
                addbyObj.add(entity.getStudentGradeOne());
                addbyObj.add(entity.getStudentGradeTwo());
                addbyObj.add(entity.getStudentGradeDemo());
                addbyObj.add(entity.getStudentGradeTotal());
                addbyObj.add(entity.getStudentGradeAvg());
                addbyObj.add(entity.getStudentStatus());
                model.addRow(addbyObj);
            }
        }
        while (studentModelArrayList.size() != 0) {
            studentModelArrayList.remove(0);
        }

        return model;
    }

    @Override
    public DefaultTableModel searchStudentInTheTable(String studentCode, DefaultTableModel searchModel) {
        for (int i = 0; i < searchModel.getRowCount(); i++) {
            if (!studentCode.equals(searchModel.getValueAt(i, 0))) {  //Find Student Code
                searchModel.removeRow(i);
                i = -1;
            }
        }
        return searchModel;
    }

    @Override
    public DefaultTableModel Descending(DefaultTableModel model) {
        Collections.reverse(studentStoredList);
        if (studentModelArrayList.size() != 0) {
            for (StudentModel entity : studentModelArrayList) {
                Vector<Object> addbyObj = new Vector<Object>();
                addbyObj.add(entity.getStudentID());
                addbyObj.add(entity.getStudentName());
                addbyObj.add(entity.getStudentGradeOne());
                addbyObj.add(entity.getStudentGradeTwo());
                addbyObj.add(entity.getStudentGradeDemo());
                addbyObj.add(entity.getStudentGradeTotal());
                addbyObj.add(entity.getStudentGradeAvg());
                addbyObj.add(entity.getStudentStatus());
                model.addRow(addbyObj);
            }
        } else {
            while (model.getRowCount() != 0) {
                model.removeRow(0);
            }
            System.out.println(model.getRowCount());
            for (StudentModel data : studentStoredList) {
                Vector<Object> addbyObj = new Vector<Object>();
                addbyObj.add(data.getStudentID());
                addbyObj.add(data.getStudentName());
                addbyObj.add(data.getStudentGradeOne());
                addbyObj.add(data.getStudentGradeTwo());
                addbyObj.add(data.getStudentGradeDemo());
                addbyObj.add(data.getStudentGradeTotal());
                addbyObj.add(data.getStudentGradeAvg());
                addbyObj.add(data.getStudentStatus());
                model.addRow(addbyObj);
            }
        }
//        while (studentModelArrayList.size() != 0) {
//            studentModelArrayList.remove(0);
//        }
        return model;
    }

    @Override
    public DefaultTableModel deleteStudentInTheTable(String studentCode, DefaultTableModel model, String path) {
        for (int i = 0; i < model.getRowCount(); i++) {
            if (studentCode.equals(model.getValueAt(i, 0))) {
                model.removeRow(i);
                studentStoredList.remove(i);
                i = -1;
            }
        }
        try {
            File file = new File(path);
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (int i = 0; i < model.getRowCount(); i++) {
                bufferedWriter.write(model.getValueAt(i, 0) + "," + model.getValueAt(i, 1) + "," + model.getValueAt(i, 2) + "," + model.getValueAt(i, 3) + "," + model.getValueAt(i, 4));
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        } catch(DirectoryNotEmptyException e){
            JOptionPane.showMessageDialog(null, "Can not overwrite file");
        } catch (IOException e) {
           JOptionPane.showMessageDialog(null, "Error");
        } catch(SecurityException e){
            JOptionPane.showMessageDialog(null, "Can not overwrite file");
        }
        JOptionPane.showMessageDialog(null, "Delete Success");
//        String filePath = "saveDirectory.txt";
//        String removeTerm = studentCode;
//        File inputFile = new File(filePath);
//        File tempFile = new File("myTempFile.txt");
//        String studentID = "";
//        String studentName = "";
//        String gradeOne = "";
//        String gradeTwo = "";
//        String gradeDemo = "";
//        String gradeTotal = "";
//        String gradeAvg = "";
//        String gradeStatus = "";
//        try {
//            FileWriter fw = new FileWriter(tempFile, true);
//            BufferedWriter bw = new BufferedWriter(fw);
//            PrintWriter pw = new PrintWriter(bw);
//            Scanner x = new Scanner(new File(filePath));
//            x.useDelimiter("[,\n]");
//            while (x.hasNext()) {
//                studentID = x.next();
//                studentName = x.next();
//                gradeOne = x.next();
//                gradeTwo = x.next();
//                gradeDemo = x.next();
//                gradeTotal = x.next();
//                gradeAvg = x.next();
//                gradeStatus = x.next();
//                if (!studentID.equals(removeTerm)) {
//                    pw.println(studentID + "," + studentName + "," + gradeOne + "," + gradeTwo + "," + gradeDemo + "," + gradeTotal + "," + gradeAvg + "," + gradeStatus);
//                }
//            }
//            x.close();
//            pw.flush();
//            pw.close();
//            bw.close();
//            fw.close();
//            inputFile.delete();
//            File dump = new File(filePath);
//            tempFile.renameTo(dump);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        } catch (Exception e) {
//            System.out.println(e);
//        }
        return model;
    }

    @Override
    public void saveStudentInTheTable() {
        Object o = studentStoredList;
        File file = null;
        JFileChooser chooser = new JFileChooser();
        int result = chooser.showSaveDialog(studentView);
        if (result == JFileChooser.APPROVE_OPTION) {
            file = chooser.getSelectedFile();
        }
        if (file != null) {
            if (file.getName().endsWith(".txt")) {
                try {
                    FileOutputStream fos = new FileOutputStream(file);
                    ObjectOutputStream oos = new ObjectOutputStream(fos);
                    oos.writeObject(o);
                    oos.close();
                    fos.close();
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(studentView, "Error: " + e.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(studentView, "Please save file in txt");
            }
        }

    }

    @Override
    public DefaultTableModel loadStudentInTheTable(DefaultTableModel model) {
        try {
            File file = null;
            JFileChooser chooser = new JFileChooser();
            int result = chooser.showOpenDialog(studentView);
            if (result == JFileChooser.APPROVE_OPTION) {
                file = chooser.getSelectedFile();
                if (file.getName().endsWith(".txt")) {
                    FileInputStream inputFileFetch = new FileInputStream(file);
                    ObjectInputStream outputFileFetch = new ObjectInputStream(inputFileFetch);
                    studentStoredList = (ArrayList<StudentModel>) outputFileFetch.readObject();
                    outputFileFetch.close();
                    inputFileFetch.close();
                    model = displayStudentIntoTable(model);
                } else {
                    JOptionPane.showMessageDialog(studentView, "File must be txt file");
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(studentView, "File not found");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(studentView, "File not found");
            e.printStackTrace();
        }
        model = displayStudentIntoTable(model);
        return model;
    }

    @Override
    public DefaultTableModel updateStudentInTheTable(String studentCode, DefaultTableModel model) {
        for (int i = 0; i < model.getRowCount(); i++) {
            if (studentCode.equals(model.getValueAt(i, 0))) {
                studentView.getTextStudentCode().setText(model.getValueAt(i, 0).toString());
                studentView.getTextStudentName().setText(model.getValueAt(i, 1).toString());
                studentView.getTextStudentGradeOne().setText(model.getValueAt(i, 2).toString());
                studentView.getTextStudentGradeTwo().setText(model.getValueAt(i, 3).toString());
                studentView.getTextStudentGradeDemo().setText(model.getValueAt(i, 4).toString());
                model.removeRow(i);
                studentStoredList.remove(i);
                i = -1;
            }
        }
        return model;
    }
}


