package view;

import model.LimitsTable;
import controller.BranchAndBound;
import model.Node;
import model.Limit;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by MaciekBihun on 2016-05-22.
 * 1. Tablica ograniczeń- klasa
 * 2. Tablica Simpleks
 *  - korzysta z tablicy ograniczeń
 */
public class Main {

    private static int counter = 0;
    private static BranchAndBound branchBound;
    private static JPanel visualPanel;
    public Main(JPanel visualPanel){
        this.visualPanel = visualPanel;
    }
    public static void main(String[] args)  {
        //Podaj funkcje
        List<Integer> functionParameters = new ArrayList<Integer>(Arrays.asList(-7, 8, 10));

        //Podaj ograniczenia
        double [] limitPars1 = {2.3, 3.1, 2};
        double [] limitPars2 = {1, 1.1, 2};
        double [] limitPars3 = {-1, 1, 2};

        String equationSign1 = "<=";
        String equationSign2 = "<=";
        String equationSign3 = "<=";

        double result1 = 1000;
        double result2 = 800;
        double result3 = 650;

        //Ustaw ograniczenia
        Limit limit1 = new Limit(limitPars1, equationSign1, result1);
        Limit limit2 = new Limit(limitPars2, equationSign2, result2);
        Limit limit3 = new Limit(limitPars3, equationSign3, result3);

        List<Limit> limitsList  = new ArrayList<Limit>();
        limitsList.add(limit1);
        limitsList.add(limit2);
        limitsList.add(limit3);

        LimitsTable limitsTable = new LimitsTable(functionParameters, limitsList);

        branchBound = BranchAndBound.getInstance(limitsTable, visualPanel);
        Node startNode = branchBound.getStartingNode();

        double [] results;
        //Jeśli nie ma nie całkowitych wartości
        if(startNode.getNotIntegerIndex() == -1){
            System.out.println("Nie było nie całkowitych elementów : ");
            results = startNode.getNodeVariables();
        } else {
            System.out.println("Są nie całkowite elementy : ");
            results = searchForResults(startNode);
        }
        printResults(results);
    }

    /**
     * Prints all results table to the console
     * @param results
     */
    public static void printResults(double [] results){
        for (int i = 0; i < results.length; i++) {
            System.out.println("Zmienna :" + results[i]);
        }
    }

    /**
     *
     * @param node is a starting node for algorithm.
     * @return Array with integer results.
     */
    public static double [] searchForResults(Node node){
        counter++;
        System.out.println("Ilość operacji : " + counter);

        if (counter > 10)
            return node.getNodeVariables();

        System.out.println("To dochodzi ? ");
        //Create two new nodes
        Node lowerNode = branchBound.createLowerNode(node);
        Node upperNode = branchBound.createUpperNode(node);

        //prints those nodes in panel
        branchBound.printNode(lowerNode);
        branchBound.printNode(upperNode);

        //lowerNode.printLimitTable();
        System.out.println("Zmienne noda : ");
        for (int i = 0; i < lowerNode.getNodeVariables().length; i++) {
            System.out.print(lowerNode.getNodeVariables()[i] + " ");
        }
        System.out.println();


        //Print two new nodes in a panel.
        branchBound.printNode(lowerNode);
        branchBound.printNode(upperNode);

        //If there is no not integer varialbes then return results.
        if(lowerNode.getNotIntegerIndex() == -1){
            return lowerNode.getNodeVariables();
        } else if(upperNode.getNotIntegerIndex() == -1){
            return upperNode.getNodeVariables();
        }

        //Choosee which road to folow.
        if(upperNode.getNodeSum() > lowerNode.getNodeSum()){
            searchForResults(upperNode);
        } else {
            searchForResults(lowerNode);
        }

        return node.getNodeVariables();
    }
}
