import model.Limit;
import model.SimplexTable;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by MaciekBihun on 2016-05-20.
 */
public class SimplexTableTest {

    List<Integer> listaParametrow = new ArrayList<Integer>(Arrays.asList(4, 2));
    List<Limit> limitsList;
    @Before
    public void setSimplexTableTest(){
        double [] limitPars1 = {-1 ,1};
        double [] limitPars2 = {2,1};

        String equationSign1 = "<=";
        String equationSign2 = "<=";

        double result1 = 4;
        double result2 = 6;

        Limit limit1 = new Limit(limitPars1, equationSign1, result1);
        Limit limit2 = new Limit(limitPars2, equationSign2, result2);

        limitsList = new ArrayList<Limit>();
        limitsList.add(limit1);
        limitsList.add(limit2);
        SimplexTable.getInstance().setSimTab(listaParametrow, limitsList);
    }

    @Test
    public void getSimplexTableTest(){
        for (int i = 0; i < SimplexTable.getInstance().getSimTab().length; i++) {
            for (int j = 0; j < SimplexTable.getInstance().getSimTab()[i].length; j++) {
                System.out.print(SimplexTable.getInstance().getSimTab()[i][j] + " ");
            }
            System.out.println();
        }

    }

    //passed
    @Test
    public void getMinElementTest(){
        System.out.println(SimplexTable.getInstance().getMinElementColumn());
    }

    //passed
    @Test
    public void getRowResultTest(){
        assertEquals(SimplexTable.getInstance().getRowResult(1), 6, 0);
        System.out.println("Row result : " + SimplexTable.getInstance().getRowResult(1));
    }

    //passed
    @Test
    public void getMaxElementRowTest(){
        System.out.println("Get max element row : " + SimplexTable.getInstance().getMaxElementRow());
        assertEquals(SimplexTable.getInstance().getMaxElementRow(), 1, 0);
    }

    @Test
    public void divideSelectedRowTest(){
        System.out.println("Przed podziałem...");
        int maxRow = SimplexTable.getInstance().getMaxElementRow();
        SimplexTable.getInstance().printSelectedRow(maxRow);
        System.out.println("Po podziale... ");
        SimplexTable.getInstance().divideSelectedRow();
        SimplexTable.getInstance().printSelectedRow(maxRow);
    }

}
