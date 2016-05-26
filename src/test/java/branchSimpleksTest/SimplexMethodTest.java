package branchSimpleksTest;

import controller.simplex.SimplexMethod;
import model.LimitsTable;
import model.Limit;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by MaciekBihun on 2016-05-24.
 */
public class SimplexMethodTest {

    SimplexMethod simplexMethod;
    @Before
    public void initalize(){
        List<Integer> functionParameters = new ArrayList<Integer>(Arrays.asList(7, 8, 10));

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

        LimitsTable limitsTable = new LimitsTable(functionParameters,limitsList);
        simplexMethod = new SimplexMethod(limitsTable.getLimTab(), functionParameters, limitsList);

    }

    /*//passed
    @Test
    public void getSimTabTest(){
        System.out.println("Print all table test: ");
        simplexMethod.printAllTable();
    }

    //passed
    @Test
    public void getRowResultTest(){
        System.out.println("Row result : " + simplexMethod.getRowResult(1));
    }

    //passed
    @Test
    public void getMinColumnTest(){
        System.out.println("Get min column : " + simplexMethod.getMinElementColumn());
    }

    //passed
    @Test
    public void getmaxElementRowTest(){
        System.out.println("get max element Row : " + simplexMethod.getMaxElementRow());
    }


    @Test
    public void finalResults(){
        System.out.println("Final Results : ");
        simplexMethod.eliminateBelowZeros();
        System.out.println();
    }

    @Test
    public void getResultsArrayTest(){
        simplexMethod.eliminateBelowZeros();
        for (int i = 0; i < simplexMethod.getResultsArray().length; i++) {
            System.out.println("Wynik : " + simplexMethod.getResultsArray()[i]);
        }

    }*/

    //passed
    /*@Test
    public void adjustAllTableTest(){
        System.out.println("All table before adjust : ");
        simplexMethod.printAllTable();
        System.out.println("All table after adjust");
        simplexMethod.adjustAllTable();
        simplexMethod.printAllTable();
    }*/

    //Nie zwraca wyników  dobrej kolejności.
    @Test
    public void eliminateAllZeros(){
        System.out.println("Before simplex method : ");
        simplexMethod.printAllTable();
        System.out.println("Simplex Method");
        simplexMethod.eliminateBelowZeros();
        System.out.println("Wyniki działania : ");
        for (int i = 0; i < simplexMethod.getResultsArray().length; i++) {
            System.out.println("Element: " + simplexMethod.getResultsArray()[i]);
        }

        System.out.println("Pokaż zawartość tabelek etykiet : ");
        for (int i = 0; i < simplexMethod.getHorizontalTable().length; i++) {
            System.out.print( simplexMethod.getHorizontalTable()[i] + " ");
        }
        System.out.println();
        for (int i = 0; i < simplexMethod.getVerticalTable().length; i++) {
            System.out.print(simplexMethod.getVerticalTable()[i]+ " ");
        }

    }

}
