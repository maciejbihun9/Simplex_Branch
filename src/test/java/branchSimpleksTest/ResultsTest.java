package branchSimpleksTest;

import controller.simplex.Results;
import controller.simplex.SimplexMethod;
import model.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by MaciekBihun on 2016-05-25.
 */
public class ResultsTest {

    Results results;
    SimplexMethod simplexMethod;
    @Before
    public void initialize(){
        List<Integer> functionParameters = new ArrayList<Integer>(Arrays.asList(7, 6, -2));

        //Podaj ograniczenia
        double [] limitPars1 = {-2.5, -1.5, 0.5};
        double [] limitPars2 = {4, 3, 2};
        double [] limitPars3 = {0, 0, -2};

        String equationSign1 = "<=";
        String equationSign2 = "<=";
        String equationSign3 = "<=";

        double result1 = 2.5;
        double result2 = 3;
        double result3 = 4;

        //Ustaw ograniczenia
        Limit limit1 = new Limit(limitPars1, equationSign1, result1);
        Limit limit2 = new Limit(limitPars2, equationSign2, result2);
        Limit limit3 = new Limit(limitPars3, equationSign3, result3);

        List<Limit> limitsList  = new ArrayList<Limit>();
        limitsList.add(limit1);
        limitsList.add(limit2);
        limitsList.add(limit3);

        model.LimitsTable limitsTable = new model.LimitsTable(functionParameters,limitsList);
        simplexMethod = new SimplexMethod(limitsTable.getLimTab(), functionParameters, limitsList);
        results = new Results(limitsTable);
    }

    //passed
    @Test
    public void isBelowZeroTest(){
        System.out.println("Is below zero : " + results.isBelowZero());
    }

    //passed
    @Test
    public void printLastRowTest(){
        results.printLastRow();
        System.out.println();
        results.countSimplexTable(1,1);
        results.printLastRow();
    }

    //passed
    @Test
    public void devideSelectedRowTest(){
        System.out.println("Divide row with zeros : ");
        results.divideSelectedRow(2,1);
        results.printAllTable();
    }

    @Test
    public void getMinElementRowTest(){
        System.out.println("Get min element row : " + results.getMinElementRow());
    }

    //passed
    @Test
    public void countSimplexTableTest(){
        System.out.println("Wypisz całą tablice przed podziałem : ");
        results.printAllTable();
        results.countSimplexTable(1,0);
        System.out.println("Wypisz całą tablice po podziale : ");
        results.printAllTable();
        System.out.println();
    }

    //passed
    @Test
    public void checkIfContainZeros(){
        System.out.println("Is below zero : " + results.isBelowZero());
    }


}
