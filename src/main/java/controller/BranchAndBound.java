package controller;

import controller.simplex.SimplexMethod;
import model.LimitsTable;
import model.Limit;
import model.Node;

import javax.swing.*;
import java.util.List;

/**
 * Created by MaciekBihun on 2016-05-22.
 */
public class BranchAndBound {
    private LimitsTable limitsTable;
    private Tree tree;
    private JPanel visualPanel;

    private BranchAndBound(LimitsTable limitsTable, JPanel visualPanel) {
        this.limitsTable = limitsTable;
        this.visualPanel = visualPanel;
        initializeTree();
    }

    public static BranchAndBound getInstance(LimitsTable limitsTable, JPanel visualPanel) {
        return new BranchAndBound(limitsTable, visualPanel);
    }

    private void initializeTree(){
        Node node = getStartingNode();
        //Create tree
        tree = new Tree(node, visualPanel);
    }

    public Node getStartingNode() {
        List<Limit> limitList = limitsTable.getLimitList();
        List<Integer> functionParameters = limitsTable.getFunctionParameters();
        double [][] simTab = limitsTable.getLimTab();
        //Count simplex
        SimplexMethod simplexMethod = new SimplexMethod(simTab, functionParameters, limitList);
        double [] simplexResults = simplexMethod.getResultsArray();

        return new Node(simplexResults, limitsTable);
    }

    public Node createLowerNode(Node node){
        return tree.createLowerNode(node);
    }

    public Node createUpperNode(Node node){
        return tree.createUpperNode(node);
    }

    public double getNodeResult(Node node){
        return node.getNodeSum();
    }

    public void printNode(Node node){
        tree.printNode(node);
    }


}
