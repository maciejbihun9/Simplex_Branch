package controller.simplex;

import model.Limit;
import model.LimitsTable;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by MaciekBihun on 2016-05-19.
 */
public class SimplexMethod {

    private Results results;

    //parametry tableki simpleks
    private List<Integer> functionParameters;
    private List<Limit> limitList;

    //Rozwiązania
    private int horizontalTable[];
    private int verticalTable[];

    //Tablica simpleks
    private double [][] simTab;

    //Wielkość tablicy
    private int simTabHeight;
    private int simTabWidth;

    //-----------------BASIC OPERATIONS WITH SIMPLEX TABLE-----------------------//
    public SimplexMethod(double [][] simTab, List<Integer> functionParameters, List<Limit> limitList){
        this.simTab = simTab;
        this.functionParameters = functionParameters;
        this.limitList = limitList;

        LimitsTable limitsTable = new LimitsTable(functionParameters, limitList);
        this.results = new Results(limitsTable);
    }

    public double [] getResultsArray(){
        eliminateBelowZeros();
        return results.getResultsArray();
    }


    public int [] getHorizontalTable(){
        return results.getHorizontalTable();
    }

    public int [] getVerticalTable(){
        return results.getVerticalTable();
    }


    /**
     * prints all table in console
     */
    public void printAllTable() {
        results.printAllTable();
    }

    /**
     *
     * @return Column index with the lowest function parameter.
     */
    public int getMinElementColumn(){
        return results.getMinElementColumn();
    }

    public void adjustAllTable(){
        //Dobre miejsce na podmiane zmiennych.

        int columnIndex = results.getMinElementColumn();
        //int rowIndex = results.getMaxElementRow();
        int rowIndex = results.getMinElementRow();

       results.countSimplexTable(rowIndex, columnIndex);

    }


    /**
     * Eliminate all zeros in last row
     */
    private boolean isBelowZero(){
        return results.isBelowZero();
    }


    //-------------------------SETTING RESULTS FUNCTIONALITIES------------------//


    public void eliminateBelowZeros(){
        int counter = 0;
        while (isBelowZero()){
            counter++;
            if(counter > 10)
                break;
            System.out.println("Wartość counter : " + counter);

            adjustAllTable();
            printAllTable();
        }
    }

}
