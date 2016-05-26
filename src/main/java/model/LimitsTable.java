package model;

import java.util.List;

/**
 * Created by MaciekBihun on 2016-05-22.
 */
public class LimitsTable {

    private  List<Integer> functionParameters;
    private  List<Limit> limitList;
    private  double [][] limTab;

    public LimitsTable(List<Integer> functionParameters, List<Limit> limitList){
        this.limitList = limitList;
        this.functionParameters = functionParameters;
        setLimTab();
    }

    public  List<Integer> getFunctionParameters(){
        return functionParameters;
    }

    public  List<Limit> getLimitList(){
        return limitList;
    }

    public  double [][] getLimTab(){
        return limTab;
    }

    public  void printLimTab(){
        for (int i = 0; i < limTab.length; i++) {
            for (int j = 0; j < limTab[0].length; j++) {
                System.out.print(limTab[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void setLimTab(){

        int simTabHeight = limitList.size() + 1;
        int simTabWidth = functionParameters.size() + simTabHeight;

        limTab = new double[simTabHeight][simTabWidth];

        //wiersz to i, a kolumna to j.
        int counter = functionParameters.size();
        for(int i = 0 ; i < limitList.size(); i++){
            int j = 0;
            for (j = 0; j < limitList.get(i).getLimitParameters().length; j++) {
                limTab[i][j] = limitList.get(i).getLimitParameters()[j];
            }

            limTab[i][simTabWidth - 1] = limitList.get(i).getLimitResult();

            limTab[i][counter++] = 1;

        }

        for (int k = 0; k < functionParameters.size(); k++) {
            limTab[simTabHeight - 1][k] = -functionParameters.get(k);
        }
    }
}
