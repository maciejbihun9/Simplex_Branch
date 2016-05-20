import org.junit.Before;
import model.Limit;
import org.junit.Test;

/**
 * Created by MaciekBihun on 2016-05-19.
 */
public class LimitTest {

    double [] params = {2.2, 3.1, 4.5, 2};
    String equationSign = ">=";
    double result = 12.3;
    Limit limit;

    @Before
    public void initialize(){
        limit = new Limit(params, equationSign, result);
    }

    @Test
    public void adjustLimitParamsTest(){
        for (int i = 0; i < limit.getLimitParameters().length; i++) {
            System.out.println(limit.getLimitParameters()[i]);
        }
        System.out.println("Znak nierówności : " + limit.getEquationSign());
        System.out.println("Rezultat nierówności: " + limit.getLimitResult());
    }

}
