package ManagementSystem;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.JTable;
import java.util.InputMismatchException;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

@Getter
@Setter
public
class StudentView extends JFrame {
    private JLabel labelStudentID, labelStudentName, labelStudentGradeASM1, labelStudentGradeASM2, labelStudentGradeDemo, labelStudentStatus;
    private JLabel[] allLabel;
    private JButton enterButton, displayStudentButton, searchingButton, sortingButton, deleteButton, saveButton, openButton, refreshButton, updateButton, exitButton;
    private JButton[] studentButtonGroup;
    protected JTextField textStudentCode, textStudentName, textStudentGradeOne, textStudentGradeTwo, textStudentGradeDemo;
    public static JTextField[] studentTextFieldGroup;
    public static DefaultTableModel model;
    private Vector<String> head;
    private JTable table;
    public StudentList studentList;

    public StudentView() throws HeadlessException {
        this.init();
        setVisible(true);
    }

    public void init() {
        this.setTitle("Student Management System");
        this.setSize(1200, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setBackground(Color.WHITE);
        studentList = new StudentList(this);

        ActionListener actionListener = new StudentController(this);

        Font font = new Font("Arial", Font.BOLD, 20);
        Color color = new Color(Color.PINK.getRGB());
        Color color2 = new Color(Color.WHITE.getRGB());
        JPanel bottom = new JPanel(new GridLayout(1, 5, 0, 0));
        enterButton = new JButton("Enter");
        enterButton.setFont(font);
        enterButton.setForeground(color2);
        enterButton.setBackground(color);
        displayStudentButton = new JButton("Output");
        displayStudentButton.setFont(font);
        displayStudentButton.setForeground(color2);
        displayStudentButton.setBackground(color);
        searchingButton = new JButton("Search");
        searchingButton.setFont(font);
        searchingButton.setForeground(color2);
        searchingButton.setBackground(color);
        sortingButton = new JButton("Sort");
        sortingButton.setFont(font);
        sortingButton.setForeground(color2);
        sortingButton.setBackground(color);
        deleteButton = new JButton("Delete");
        deleteButton.setFont(font);
        deleteButton.setForeground(color2);
        deleteButton.setBackground(color);
        saveButton = new JButton("Save");
        saveButton.setFont(font);
        saveButton.setForeground(color2);
        saveButton.setBackground(color);
        openButton = new JButton("Open");
        openButton.setFont(font);
        openButton.setForeground(color2);
        openButton.setBackground(color);
        refreshButton = new JButton("Refresh");
        refreshButton.setFont(font);
        refreshButton.setForeground(color2);
        refreshButton.setBackground(color);
        updateButton = new JButton("Update");
        updateButton.setFont(font);
        updateButton.setForeground(color2);
        updateButton.setBackground(color);
        exitButton = new JButton("Exit");
        exitButton.setFont(font);
        exitButton.setForeground(color2);
        exitButton.setBackground(color);
        studentButtonGroup = new JButton[]{enterButton, displayStudentButton, searchingButton, sortingButton, deleteButton, saveButton, openButton, refreshButton, updateButton, exitButton};

        for (int i = 0; i < studentButtonGroup.length; i++) {
            bottom.add(studentButtonGroup[i]);
        }
        add("South", bottom);

        //Create table
        head = new Vector<String>();
        head.add("Student ID");
        head.add("Name");
        head.add("Grade of ASM1");
        head.add("Grade of ASM2");
        head.add("Demo Grade");
        head.add("Total Grade");
        head.add("Average");
        head.add("Status");
        model = new DefaultTableModel(head, 0);
        table = new JTable(model);
        JScrollPane scroll = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        table.setBackground(Color.LIGHT_GRAY);
        table.setForeground(Color.BLACK);
        table.setFont(new Font("Arial", Font.ITALIC, 12));
        table.setGridColor(Color.DARK_GRAY);
        table.setRowHeight(30);

        JPanel textFieldPanel = new JPanel(new GridLayout(1, 2, 0, 0));
        JPanel labelPanel = new JPanel(new GridLayout(5, 2, 0, 0));
        JPanel[] panelArray = new JPanel[5];
        for (int i = 0; i < panelArray.length; i++) {
            panelArray[i] = new JPanel();
        }

        Color color3 = new Color(Color.red.getRGB());
        Font font2 = new Font("Arial", Font.BOLD, 15);
        labelStudentID = new JLabel("Student ID");
        labelStudentID.setForeground(color3);
        labelStudentID.setFont(font2);
        labelStudentName = new JLabel("Student Name");
        labelStudentName.setForeground(color3);
        labelStudentName.setFont(font2);
        labelStudentGradeASM1 = new JLabel("ASM1 Grade");
        labelStudentGradeASM1.setForeground(color3);
        labelStudentGradeASM1.setFont(font2);
        labelStudentGradeASM2 = new JLabel("ASM2 Grade");
        labelStudentGradeASM2.setForeground(color3);
        labelStudentGradeASM2.setFont(font2);
        labelStudentGradeDemo = new JLabel("Demo Grade");
        labelStudentGradeDemo.setForeground(color3);
        labelStudentGradeDemo.setFont(font2);
        labelStudentStatus = new JLabel("Status");
        allLabel = new JLabel[]{labelStudentID, labelStudentName, labelStudentGradeASM1, labelStudentGradeASM2, labelStudentGradeDemo, labelStudentStatus};

        textStudentCode = new JTextField("", 20);
        textStudentName = new JTextField("", 20);
        textStudentGradeOne = new JTextField("", 20);
        textStudentGradeTwo = new JTextField("", 20);
        textStudentGradeDemo = new JTextField("", 20);
        studentTextFieldGroup = new JTextField[]{textStudentCode, textStudentName, textStudentGradeOne, textStudentGradeTwo, textStudentGradeDemo};

        try {
            for (int i = 0; i < panelArray.length; i++) {
                panelArray[i].add("West", allLabel[i]);
                panelArray[i].add("Center", studentTextFieldGroup[i]);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            System.out.println(e.getMessage());
        }

        for (int i = 0; i < 5; i++) {
            labelPanel.add(panelArray[i]);
        }
        textFieldPanel.add(labelPanel);
        textFieldPanel.add(scroll);
        add(textFieldPanel);


        for (int i = 0; i < studentButtonGroup.length; i++) {
            studentButtonGroup[i].addActionListener(actionListener);
        }
    }

    public String getStudentCode() {
        return textStudentCode.getText();
    }

    public String getStudentName() {
        return textStudentName.getText();
    }

    public int getGradeOne() {
        return Integer.parseInt(textStudentGradeOne.getText());
    }

    public int getGradeTwo() {
        return Integer.parseInt(textStudentGradeTwo.getText());
    }

    public int getGradeDemo() {
        return Integer.parseInt(textStudentGradeDemo.getText());
    }

    public void refreshStudent() {
        this.textStudentCode.setText("");
        this.textStudentName.setText("");
        this.textStudentGradeOne.setText("");
        this.textStudentGradeTwo.setText("");
        this.textStudentGradeDemo.setText("");
    }

    public static boolean isValidateGrade(String fieldText) {
        int grade = Integer.parseInt(fieldText);
        if (grade >= 0 && grade <= 10) {
            return true;
        }
        return false;
    }

    public static boolean isValidateStudentCodeContain(String fieldText) {
        String studentCode = fieldText;
        if ((studentCode.contains("GCD"))) {
            return true;
        }
        return false;
    }

    public static boolean isValidateStudentCodeSize(String fieldText) {
        String studentCode = fieldText;
        if ((studentCode.length() == 9)) {
            return true;
        }
        return false;
    }

    public static boolean isValidateStudentCodeSame(String fieldText) {
        String studentCode = fieldText;
        for (int i = 0; i < model.getRowCount(); i++) {
            if (studentCode.equals(model.getValueAt(i, 0))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isValidateStudentisCharacter(String fieldText) {
        String studentName = fieldText;
        for (int i = 0; i < studentName.length(); i++) {
            if (Character.isDigit(studentName.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isValidateStudentNameLength(String fieldText) {
        String studentName = fieldText;
        if (studentName.length() > 10 && studentName.length() <= 30) {
            return true;
        }
        return false;
    }

    public static boolean isValidateStudentFullField() {
        for (int i = 0; i < studentTextFieldGroup.length; i++) {
            if (studentTextFieldGroup[i].getText().equals("")) {
                return false;
            }
        }
        return true;
    }
}
