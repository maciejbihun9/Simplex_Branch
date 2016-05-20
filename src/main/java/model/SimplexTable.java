package model;

import java.util.List;

/**
 * Created by MaciekBihun on 2016-05-19.
 */
public class SimplexTable {

    private double [][] simTab;
    private int simTabHeight;
    private int simTabWidth;
    
    private static SimplexTable simplexTable = new SimplexTable();

    private SimplexTable(){

    }

    public static SimplexTable getInstance(){
        return simplexTable;
    }


    public double[][] getSimTab(){
        return simTab;
    }


    /**
     *
     * @param functionParameters
     * @param limitList
     */
    public void setSimTab(List<Integer> functionParameters, List<Limit> limitList){

         simTabHeight = limitList.size() + 1;
         simTabWidth = functionParameters.size() + simTabHeight;

        simTab = new double[simTabHeight][simTabWidth];
        int counter = 0;
        for(int i = 0 ; i < limitList.size(); i++){
            int j;
            for (j = 0; j < limitList.get(i).getLimitParameters().length; j++) {
                simTab[i][j] = limitList.get(i).getLimitParameters()[j];
            }

            simTab[i][simTabWidth - 1] = limitList.get(i).getLimitResult();

            simTab[i][j + counter++] = 1;
        }

        for (int k = 0; k < functionParameters.size(); k++) {
            simTab[simTabHeight - 1][k] = -functionParameters.get(k);
        }

    }

    /**
     *
     * @param whichRow
     * @return last column element in specified row.
     */
    public double getRowResult(int whichRow){
        return simTab[whichRow][simTabWidth - 1];
    }

    public void printSelectedRow(int rowIndex){
        for (int i = 0; i < simTabWidth - 1; i++) {
            System.out.print(simTab[rowIndex][i] + " ");
        }
    }


    /**
     *
     * @return Index of row with the largest profit after
     * dividing resultRow margin with min column element.
     */
    public int getMaxElementRow(){
        int minColumnIndex = getMinElementColumn();
        int maxMarginIndex = 0;
        double dividedMargin = simplexTable.getRowResult(0)/simTab[0][minColumnIndex];
        for (int i = 1; i < simTabHeight - 1; i++) {
            if(simplexTable.getRowResult(i)/simTab[i][minColumnIndex] > dividedMargin)
            dividedMargin = simplexTable.getRowResult(i)/simTab[i][minColumnIndex];
            maxMarginIndex = i;
        }
        return maxMarginIndex;
    }

    /**
     *
     * @return Column index with the lowest function parameter.
     */
    public int getMinElementColumn(){
        int minColumnIndex = 0;
        double minElement = simTab[simTabHeight - 1][0];
        for (int i = 0; i < simTabWidth - 1; i++) {
            if(simTab[simTabHeight - 1][i] < minElement){
                minElement = simTab[simTabHeight - 1][i];
                minColumnIndex = i;
            }

        }
        return minColumnIndex;
    }

    public void divideSelectedRow(){
        int columnIndex = getMinElementColumn();
        int rowIndex = getMaxElementRow();
        double fieldMargin = simTab[rowIndex][columnIndex];

        if(fieldMargin == 1){
            return;
        } else {
            double divisor = 1 / fieldMargin;

            //Divide all row elements.
            for (int i = 0; i < simTabWidth - 1; i++) {
                simTab[rowIndex][i] = simTab[rowIndex][i] * divisor;
            }
        }
    }
}
