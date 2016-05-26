package model;

import java.util.List;

/**
 * Created by MaciekBihun on 2016-05-23.
 */
public class Node {

    private LimitsTable limitsTable;
    private static int id = 0;
    private double [] nodeVariables;
    private double [][] limitsArray;

    /**
     * Tree nodes constructor.
     * @param nodeVariables
     */
    public Node(double [] nodeVariables, LimitsTable limitsTable){
        id = id + 1;
        this.limitsTable = limitsTable;
        this.nodeVariables = nodeVariables;
        this.limitsArray = limitsTable.getLimTab();
    }

    public void printLimitTable(){
        for (int i = 0; i < limitsArray.length; i++) {
            for (int j = 0; j < limitsArray[0].length; j++) {
                System.out.print(limitsArray[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     *
     * @return Count node variables sum.
     */
    public double getNodeSum(){
        double sum = 0;
        List<Integer> listaParamaterow = limitsTable.getFunctionParameters();
        for (int i = 0; i < listaParamaterow.size(); i++) {
            sum += listaParamaterow.get(i) * nodeVariables[i];
        }
        return  Math.round(sum * 100.0) / 100.0;
    }

    public List<Integer> getFunctionParameters(){
        return limitsTable.getFunctionParameters();
    }

    /**
     *
     * @return Limits list.
     */
    public List<Limit> getLimitsList(){
        return limitsTable.getLimitList();
    }

    /**
     *
     * @return Limits table imposed on Node.
     */
    public double [][] getLimitsTable(){
        return limitsTable.getLimTab();
    }

    /**
     *
     * @return Node's variables.
     */
    public double [] getNodeVariables(){
        return nodeVariables;
    }

    /**
     *
     * @return Node id.
     */
    public int getId(){
        return id;
    }

    /**
     * Draw node on panel
     */
    public void drawNode(){

    }

    /**
     *
     * @return Index with not integer value.
     * Returns -1 if there is not integer value.
     */
    public int getNotIntegerIndex(){
        int firstNotOptimalIndex = -1;
        for (int i = 0; i < nodeVariables.length; i++) {
            //jeśli liczba jest nie całkowita
            if (!((nodeVariables[i] == Math.floor(nodeVariables[i])) &&
                    !Double.isInfinite(nodeVariables[i]))) {
                //dodaj ją do tablicy liczb nie całkowitych.
                firstNotOptimalIndex = i;
                return firstNotOptimalIndex;
            }
        }
        return firstNotOptimalIndex;
    }


}
