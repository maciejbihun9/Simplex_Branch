package controller;

import controller.simplex.SimplexMethod;
import model.*;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Created by MaciekBihun on 2016-05-23.
 */
public class Tree {

    private Graphics graphics;
    private Node startingNode;
    private LimitsTable limitsTable;
    private JPanel visualPanel;

    public Tree(Node startingNode, JPanel visualPanel){
        this.startingNode = startingNode;
        this.visualPanel = visualPanel;
    }

    public Tree(Node startingNode){
        this.startingNode = startingNode;
    }

    public void addNodeToRepo(Node node){
        SumRepository.getInstance().addToRepository(node);
    }

    public Node createLowerNode(Node beforeNode){

        //Stwórz nową tabelkę ograniczeńbe
        List <Limit> newLimits =  branchDown(beforeNode);
        LimitsTable newLimitTable = new LimitsTable(beforeNode.getFunctionParameters(), newLimits);

        //Tworzenie tablicy simpleks z nowymi ograniczeniami.
        SimplexMethod simplexMethod = new SimplexMethod(newLimitTable.getLimTab(), beforeNode.getFunctionParameters(), newLimits);
        double [] simplexResults = simplexMethod.getResultsArray();


        //Podaj nowe rozwiązania wraz z nowymi ograniczeniami.
        return new Node(simplexResults, newLimitTable);
    }

    public Node createUpperNode(Node beforeNode){

        //Stwórz nową tabelkę ograniczeńbe
        List <Limit> newLimits =  branchUp(beforeNode);
        LimitsTable newLimitTable = new LimitsTable(beforeNode.getFunctionParameters(), newLimits);

        //Tworzenie tablicy simpleks z nowymi ograniczeniami.
        SimplexMethod simplexMethod = new SimplexMethod(newLimitTable.getLimTab(), beforeNode.getFunctionParameters(), newLimits);
        double [] simplexResults = simplexMethod.getResultsArray();

        //Podaj nowe rozwiązania wraz z nowymi ograniczeniami.
        return new Node(simplexResults, newLimitTable);
    }

    /**
     *
     * @param node
     * @return Index of row with parameter to branch
     */
    private int getIndexOfRow(Node node){
        int index = node.getNotIntegerIndex();

        double [][] limitsTab = node.getLimitsTable();
        int equationWithThisVar = 0;

        //Weź pierwsze równanie, które nie posiada zera w pozycji danej zmiennej
        for(int i = 0 ;i < limitsTab.length - 1; i++){ ///wiersz 1
            if(limitsTab[i][index] != 0){
                equationWithThisVar = i;
                break;
            }
        }
        return equationWithThisVar;
    }

    public List<Limit> branchUp(Node node){

        int index = node.getNotIntegerIndex();
        double [] nodeVariables = node.getNodeVariables();
        int rowIndex = getIndexOfRow(node);

        int limitLength = node.getLimitsList().get(rowIndex).getLimitParameters().length;
        double limitParams[] = new double[limitLength + 1];
        for(int j = 0; j < limitLength; j++){
            //change sign of variable.
            limitParams[j] = -node.getLimitsList().get(rowIndex).getLimitParameters()[j];
        }

        //Ustaw na ostatnim jedynke
        List<Limit> limitList = node.getLimitsList();
        double limitResult = Math.ceil(nodeVariables[index]) - limitList.get(rowIndex).getLimitResult();
        limitList.add(new Limit(limitParams, "<=",  limitResult));

        return  limitList;
    }

    public List<Limit> branchDown(Node node){

        int index = node.getNotIntegerIndex();
        double [] nodeVariables = node.getNodeVariables();
        int rowIndex = getIndexOfRow(node);

        int limitLength = node.getLimitsList().get(rowIndex).getLimitParameters().length;
        double limitParams[] = new double[limitLength + 1];
        for(int j = 0; j < limitLength; j++){
            //change sign of variable.
            limitParams[j] = -node.getLimitsList().get(rowIndex).getLimitParameters()[j];
        }

        //Ustaw na ostatnim jedynke
        List<Limit> limitList = node.getLimitsList();
        double limitResult = Math.floor(nodeVariables[index]) - limitList.get(rowIndex).getLimitResult();
        limitList.add(new Limit(limitParams, "<=",  limitResult));

        return  limitList;
    }

    public void printNode(Node node){
        node.drawNode();
    }

    public void drawNode(Node node){
        graphics.setColor(Color.blue);
    }

}
