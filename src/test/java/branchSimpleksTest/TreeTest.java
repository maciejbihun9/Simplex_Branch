package branchSimpleksTest;

import controller.BranchAndBound;
import model.*;
import controller.Tree;
import model.LimitsTable;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by MaciekBihun on 2016-05-24.
 */
public class TreeTest {

    model.LimitsTable limitsTable;
    BranchAndBound branchAndBound;
    private double [] startingVariables = {2, 3, 1.2};
    private Node startNode;
    private Tree tree;
    @Before
    public void initialize(){
        List<Integer> listaParametrow = new ArrayList<Integer>(Arrays.asList(7, 8, 10));

        //Podaj ograniczenia
        double [] limitPars1 = {2, 3, 2};
        double [] limitPars2 = {1, 1, 2};

        String equationSign1 = "<=";
        String equationSign2 = "<=";

        double result1 = 1000;
        double result2 = 800;

        //Ustaw ograniczenia
        Limit limit1 = new Limit(limitPars1, equationSign1, result1);
        Limit limit2 = new Limit(limitPars2, equationSign2, result2);

        List<Limit> limitsList  = new ArrayList<Limit>();
        limitsList.add(limit1);
        limitsList.add(limit2);

        limitsTable = new LimitsTable(listaParametrow, limitsList);

        startNode = new Node(startingVariables ,limitsTable);
        tree = new Tree(startNode);

        /*branchAndBound = BranchAndBound.getInstance(limitsTable);
        Node startNode = branchAndBound.getStartingNode();*/


    }

   /* @Test
    public void createLowerNodeTest(){
        System.out.println("tablica ograniczeń przed utworzenie pod noda : ");
        startNode.printLimitTable();
        System.out.println();
        System.out.println("tablica ograniczen po utworzeniu podNoda : ");
        Node newNode = tree.createLowerNode(startNode);
        newNode.printLimitTable();

    }*/

    /*@Test
    public void createUpperNode(){
        System.out.println("tablica ograniczeń przed utworzenie pod noda : ");
        startNode.printLimitTable();
        System.out.println();
        System.out.println("tablica ograniczen po utworzeniu podNoda : ");
        Node newNode = tree.createUpperNode(startNode);
        newNode.printLimitTable();
        System.out.println();

    }*/

    /*//passed
    @Test
    public void getStartingNodeTest(){
        System.out.println("Print starting node limits table");
        Node node = branchAndBound.getStartingNode();
        node.printLimitTable();
        System.out.println();
    }*/

   /* @Test
    public void getStartingNodeTest(){
        Node startNode = branchAndBound.getStartingNode();
        for (int i = 0; i < startNode.getNodeVariables().length; i++) {
            System.out.println("Zmienna : " + startNode.getNodeVariables()[i]);
        }

    }*/


    //Powinien obliczyc wartość rozwiązania z tableki simplex.
    //Rozwiązanie dodać do noda
    @Test
    public void createLowerNodeTest(){
        System.out.println("Początkowa tablica : ");
        limitsTable.printLimTab();
        System.out.println("Branch new Node : ");
        Node newNode = branchAndBound.createLowerNode(startNode);
        System.out.println();
        newNode.printLimitTable();
        System.out.println();
    }

    //passed
    @Test
    public void branchUpTest(){
        List<Limit> limitsList = tree.branchUp(startNode);
        System.out.println("Lista ograniczeń...");
        for (int i = 0; i < limitsList.size(); i++) {
            for (int j = 0; j < limitsList.get(i).getLimitParameters().length; j++) {
                System.out.print(limitsList.get(i).getLimitParameters()[j] + " ");
            }
            System.out.println();
        }


    }

    //passed
    @Test
    public void branchDownTest(){
        List<Limit> limitsList = tree.branchDown(startNode);
        System.out.println("Lista ograniczeń...");
        for (int i = 0; i < limitsList.size(); i++) {
            for (int j = 0; j < limitsList.get(i).getLimitParameters().length; j++) {
                System.out.print(limitsList.get(i).getLimitParameters()[j] + " ");
            }
            System.out.println();
        }


    }



    //----------------------NODE TESTS-------------------------

    /*//passed
    @Test
    public void printLimitTable(){
        System.out.println("Node limit table : ");
        startNode.printLimitTable();
    }

    //passed
    @Test
    public void getNodeSumTest(){
        System.out.println("node sum : " + startNode.getNodeSum());
    }

    //passed
    @Test
    public void getNotIntegerIndex(){
        System.out.println("Not integer index  :" + startNode.getNotIntegerIndex());
    }
    //passed
    @Test
    public void getNodeVariablesTest(){
        for (int i = 0; i < startNode.getNodeVariables().length; i++) {
            System.out.println("Node variable : " + startNode.getNodeVariables()[i]);
        }

    }*/


}
