package view;


import model.Limit;
import model.OvalComponent;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by MaciekBihun on 2016-05-08.
 */
public class projectGui  {

    //Gui elements
    private JPanel main_panel;
    private JTextField amount_of_variables_field;
    private JButton amount_approve_btt;
    private JTable function_table;
    private JTable limit_table;
    private JButton addLimitBtt;
    private JButton removeLimitBtt;
    private JButton approve_function_btt;
    private JButton approve_limits;
    private JButton countButton;
    private JPanel input_panel;
    private JPanel resultsPanel;
    private JPanel properties_panel;
    private JPanel panel1;
    private JLabel limitListLabel;
    private JLabel variablesTableLabel;
    private JLabel amount_of_variable_label;
    private JPanel visualPanel;

    //Local Variables
    private List<Integer> functionParameters;
    private List<Limit> limitsList;
    private Executor executor;
    private int number_of_variables;
    private Table limitTable;
    private Table functionTable;
    private int selectedRow;

    private static JFrame frame;
    private static Rameczka rameczka;



    public projectGui(){

        amount_approve_btt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                approve_function_btt.setEnabled(true);
                setFunctionVariables();

            }
        });

        addLimitBtt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                approve_limits.setEnabled(true);
                addLimitToList();
            }
        });

        approve_function_btt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //Enable limit button.
                addLimitBtt.setEnabled(true);

                //Add columns to limit table.
                limitTable = new Table(limit_table);

                int equationSpace = 2;
                int columnNumber = number_of_variables + equationSpace;

                //Set limit table parameters.
                limitTable.addColumns(columnNumber);
                limitTable.setColumnTitle(columnNumber);

                //Set function parameters from function_table
                functionParameters = functionTable.getElementsFromRow(0, number_of_variables);
                System.out.println(functionParameters);

            }
        });
        removeLimitBtt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeLimitFromList(selectedRow);
            }
        });

        limit_table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                 selectedRow = limit_table.getSelectedRow();
            }
        });
        approve_limits.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                countButton.setEnabled(true);

                limitsList = new ArrayList<Limit>();
                int rows = limitTable.getTableModel().getRowCount();
                int columns = limitTable.getTableModel().getColumnCount();
                Integer[] parsedLimitArray;
                System.out.println("LIczba wierszy : " + rows);
                System.out.println("Liczba kolumn:" + columns);
                Limit limit;

                for (int i = 0; i < rows; i++) {
                    Object [] limitParams = new Object[columns - 2];
                    Object equationSign = null;
                    Object result =  null;
                    for (int j = 0; j < columns; j++) {
                        if(j < limitParams.length){
                            limitParams[j] = limit_table.getValueAt(i, j);
                        } else if(j == columns - 2){
                            equationSign = limit_table.getValueAt(i, j);
                        } else if(j == columns - 1){
                            result = limit_table.getValueAt(i, j);
                            System.out.println("Result: " + result);
                        }
                    }
                    //--------------------------
                    //WYNIKI
                    for (int j = 0; j < limitParams.length; j++) {
                        System.out.println("Param:" + limitParams[i]);
                    }
                    System.out.println("Equation sign: " + equationSign);
                    System.out.println("result : " + result);

                    //Parse objects array to Integer array.
                    Integer [] parsedObjects = new Integer [limitParams.length];
                    for (int k = 0; k < limitParams.length; k++) {
                        parsedObjects[k] = Integer.parseInt((String)limitParams[k]);
                    }

                    //Create new function limit and store it in limitList.

                    /*limit = new Limit(parsedObjects, (String)equationSign, Integer.parseInt((String)result));
                    limitsList.add(limit);*/
                }

            }
        });
        countButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Main main = new Main(visualPanel);
                startShaping();
            }
        });
    }


    /*private void displayResultsInResultPanel(){
        //Display result of the calculations in labels
        JLabel label = new JLabel("maciej");

        //Wprowadzanie danych do tablicy simplex.
        SimplexMethod simplexTable = new SimplexMethod(functionParameters, limitsList);
        simplexTable.setSimTab(functionParameters, limitsList);

        BranchTree branchTree = BranchTree.getInstance();
        branchTree.initizlizeTree(functionParameters, limitsList);
        branchTree.setOptimalResult(simplexTable.getSimplexResults());

        //Repaint layout to display new components.
        resultsPanel.add(label);
        resultsPanel.revalidate();
        resultsPanel.repaint();

        resultsPanel.setLayout(new BoxLayout(resultsPanel, BoxLayout.Y_AXIS));

    }*/

    private void addLimitToList(){
        if(!removeLimitBtt.isEnabled()){
            removeLimitBtt.setEnabled(true);
        }
        limitTable.addRow(new Object[]{});
    }

    private void removeLimitFromList(int whichRow){
        limitTable.removeRow(whichRow);
    }


    /**
     * Set functionTable parameters.
     */
    private void setFunctionVariables(){
        functionTable = new Table(function_table);

        //Remove all columns from table if already exists.
        if(functionTable.getTableModel().getColumnCount() > 0){
            System.out.println("Column counter : " + functionTable.getTableModel().getColumnCount());
            functionTable.getTableModel().setColumnCount(0);
        }

            Runnable r = () -> {
                String userInput = amount_of_variables_field.getText();
                if(Maths.isInteger(userInput)){
                    number_of_variables = Integer.parseInt(userInput);
                    System.out.println("String is a integer");
                    //Creates rows in JTable.
                    functionTable.addColumns(number_of_variables);
                    if(functionTable.getTableModel().getRowCount() > 0) {
                        functionTable.removeRow(0);
                    }
                    functionTable.addRow(new Object[]{});

                } else {
                    System.out.println("String is not integer");
                }

            };

            executor = Executors.newFixedThreadPool(4);
            executor.execute(r);
    }

    public void startShaping(){
        rameczka.startShaping();
    }

    public static void main(String[] args) {
        projectGui gui = new projectGui();
        frame = new JFrame("App");
        frame.setContentPane(gui.main_panel);

        rameczka = new Rameczka();
        frame.add(rameczka.view);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}

class Maths
{
    /**
     *
     * @param str
     * @return True if specified String object is a Integer
     */
    public static boolean isInteger(String str) {
        if (str == null) {
            return false;
        }
        int length = str.length();
        if (length == 0) {
            return false;
        }
        int i = 0;
        if (str.charAt(0) == '-') {
            if (length == 1) {
                return false;
            }
            i = 1;
        }
        for (; i < length; i++) {
            char c = str.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }
}
