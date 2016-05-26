package controller.simplex;

import model.Limit;
import model.LimitsTable;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by MaciekBihun on 2016-05-25.
 */
public class Results {

    //Wielkość tablicy
    private int simTabHeight;
    private int simTabWidth;

    private int [] horizontalTable;
    private int [] verticalTable;
    private LimitsTable simplexTable;
    private List<Limit> limitList;
    private List<Integer> functionParameters;
    private double [][] limTable;

    public Results(LimitsTable simplexTable){
        this.simplexTable = simplexTable;
        this.limitList = simplexTable.getLimitList();
        this.functionParameters = simplexTable.getFunctionParameters();
        this.limTable = simplexTable.getLimTab();

        simTabHeight = limitList.size() + 1;
        simTabWidth = functionParameters.size() + simTabHeight;

        setIndexTables(simTabHeight, simTabWidth, functionParameters.size());
    }

    public int [] getHorizontalTable(){
        return horizontalTable;
    }

    public int [] getVerticalTable(){
        return verticalTable;
    }

    /**
     * Ustaw tabelkęrozwiązań
     * @param tabHeight
     * @param tabWidth
     * @param paramsSize
     */
    private void setIndexTables(int tabHeight,int tabWidth, int paramsSize){
        horizontalTable = new int[tabWidth];
        verticalTable = new int[tabHeight];

        for (int i = 0; i < horizontalTable.length - 1; i++) {
            horizontalTable[i] = (i + 1);
        }

        for (int i = 0; i < verticalTable.length - 1; i++) {
            verticalTable[i] = (i + 1 + paramsSize);
        }
    }

    public void changeResultsOrder(int columnIndex, int rowIndex){
        int index = horizontalTable[columnIndex];
        horizontalTable[columnIndex] = verticalTable[rowIndex];
        verticalTable[rowIndex] = index;
    }

    /**
     *
     * @return results of the simplex method.
     */
    public double [] getResultsArray(){

        double [] resultsArray = new double[horizontalTable.length - 1];
        Map<Integer, Double> resultsMap = new TreeMap<>();
        //Wypisz poziome elementy
        for (int i = 0; i < functionParameters.size(); i++) {
            resultsMap.put(horizontalTable[i], limTable[simTabHeight - 1][i]);
        }

        for (int i = 0; i < simTabHeight - 1; i++) {
            resultsMap.put(verticalTable[i], getRowResult(i));
        }

        int counter = 0;
        for ( Map.Entry<Integer, Double> entry : resultsMap.entrySet()) {
            int key = entry.getKey();
            double value = entry.getValue();
            resultsArray[counter++] = value;
        }

        return resultsArray;
    }

    /**
     *
     * @param whichRow
     * @return last column element in specified row.
     */
    public double getRowResult(int whichRow){
        return limTable[whichRow][simTabWidth - 1];
    }

    /**
     * Round variable to a integer value.
     * @param variable
     * @return
     */
    public double roundVariable(double variable){
        double roundedVar = variable;
        //Sprawdz czy nie jest całkowita.
        if (!(variable == Math.floor(variable)) &&
                !Double.isInfinite(variable)) {
            //Jak bardzo się rózni od całkowitej?
            int pomocnicza = (int)variable;
            double roznica = variable - pomocnicza;
            if(roznica > 0.97 && roznica < 0.99999999999999999999){
                roundedVar = Math.ceil(roundedVar);
            } else if(roznica < 0.03 && roznica > 0.0000000000000000000001){
                roundedVar = Math.floor(roundedVar);
            } else if(roznica > -0.3 && roznica < -0.000000000000000000001){
                roundedVar = Math.ceil(roundedVar);
            } else if(roznica < -0.97 && roznica > -0.99999999999999999999){
                roundedVar = Math.floor(roundedVar);
            } else {
                roundedVar = round(roundedVar, 2);
            }
        }
        return roundedVar;
    }

    /**
     *
     * @param value
     * @param places
     * @return Runded double value to places after comma.
     */
    private static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    public void printLastRow(){
        for (int i = 0; i < limTable[0].length; i++) {
            System.out.print(limTable[simTabHeight - 1][i] + " ");
        }
    }

    /**
     * Checks weather last row contains zeros
     */
    public boolean isBelowZero(){
        boolean isBelowZero = false;
        for(int i = 0 ; i < limTable[0].length - 1; i++){
            if(limTable[simTabHeight - 1][i] < 0){
                isBelowZero = true;
            }

        }
        return isBelowZero;
    }

    public void countSimplexTable(int rowIndex, int columnIndex){

        divideSelectedRow(rowIndex, columnIndex);
        for (int i = 0; i < simTabHeight; i++) {

            if(i == rowIndex)
                continue;
            //poszczególna zmiennna w kolumnie bez tej z podziału
            double divisor =  - limTable[i][columnIndex];

            for (int j = 0; j < simTabWidth; j++) {
                limTable[i][j] = roundVariable(limTable[i][j] + divisor * limTable[rowIndex][j]);
                //limTable[i][j] = limTable[i][j] + divisor * limTable[rowIndex][j];
            }

        }
        changeResultsOrder(columnIndex, rowIndex);
    }

    /**
     * Divide all elements from selected row to achive
     * margin 1 in specified field.
     */
    public void divideSelectedRow(int rowIndex, int columnIndex){
        double fieldMargin = limTable[rowIndex][columnIndex];

        if(fieldMargin == 1){
            return;
        } else {

            double divisor =  1 / fieldMargin;

            //Divide all row elements.
            for (int i = 0; i < simTabWidth; i++) {

                double zmienna = Math.round(limTable[rowIndex][i] * divisor * 100);
                zmienna = zmienna / 100;
                limTable[rowIndex][i] = zmienna;
            }
        }
    }

    /**
     *
     * @return Index of row with the largest profit after
     * dividing resultRow margin with min column element.
     */
    public int getMinElementRow(){
        int minColumnIndex = getMinElementColumn();
        int maxMarginIndex = 0;
        double dividedMargin = getRowResult(0)/limTable[0][minColumnIndex];
        for (int i = 1; i < simTabHeight - 1; i++) {
            if(getRowResult(i)/limTable[i][minColumnIndex] < dividedMargin){
                double zmienna = limTable[i][minColumnIndex];
                if(zmienna == 0){
                    dividedMargin = 0;
                } else {
                    dividedMargin = getRowResult(i)/limTable[i][minColumnIndex];
                    maxMarginIndex = i;
                }

            }


        }
        return maxMarginIndex;
    }

    /**
     *
     * @return Index of row with the largest profit after
     * dividing resultRow margin with min column element.
     */
    public int getMaxElementRow(){
        int minColumnIndex = getMinElementColumn();
        int maxMarginIndex = 0;
        double dividedMargin = getRowResult(0)/limTable[0][minColumnIndex];
        for (int i = 1; i < simTabHeight - 1; i++) {
            if(getRowResult(i)/limTable[i][minColumnIndex] > dividedMargin){
                double zmienna = limTable[i][minColumnIndex];
                if(zmienna == 0){
                    dividedMargin = 0;
                } else {
                    dividedMargin = getRowResult(i)/limTable[i][minColumnIndex];
                    maxMarginIndex = i;
                }

            }


        }
        return maxMarginIndex;
    }

    /**
     *
     * @return Column index with the lowest function parameter.
     */
    public int getMinElementColumn(){
        int minColumnIndex = 0;
        double minElement = limTable[simTabHeight - 1][0];
        for (int i = 0; i < simTabWidth - 1; i++) {
            if(limTable[simTabHeight - 1][i] < minElement){
                minElement = limTable[simTabHeight - 1][i];
                minColumnIndex = i;
            }

        }
        return minColumnIndex;
    }

    /**
     * prints all table in console
     */
    public void printAllTable() {
        for (int i = 0; i < simTabHeight; i++) {
            for (int j = 0; j < simTabWidth; j++) {
                System.out.print(limTable[i][j] + " ");
            }
            System.out.println();
        }
    }






}
