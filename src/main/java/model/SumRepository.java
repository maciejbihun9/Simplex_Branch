package model;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by MaciekBihun on 2016-05-23.
 */
public class SumRepository {

    private static Map<Integer, Double> resultsMap = new TreeMap<>();
    private static SumRepository sumRepository = new SumRepository();
    private SumRepository(){

    }

    public static SumRepository getInstance(){
        return sumRepository;
    }

    public void addToRepository(Node node){
        resultsMap.put(node.getId(), node.getNodeSum());
    }

    public Map<Integer, Double> getResultsMap(){
        return resultsMap;
    }
}
