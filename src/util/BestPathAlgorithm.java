package util;

import model.AppleTree;
import model.Step;
import model.AppleGarden;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Iurii Miedviediev
 *
 * Slightly modified Dijkstra Algorithm
 * Originally from http://www.vogella.com/tutorials/JavaAlgorithmsDijkstra/article.html
 *
 * @author DruidKuma
 * @version 1.0.0
 * @since 3/28/17
 */
public class BestPathAlgorithm {
    private final List<Step> steps;
    private Set<AppleTree> settledNodes;
    private Set<AppleTree> unSettledNodes;
    private Map<AppleTree, AppleTree> predecessors;
    private Map<AppleTree, Integer> distance;

    public BestPathAlgorithm(AppleGarden appleGarden) {
        this.steps = new ArrayList<>(appleGarden.getSteps());
    }

    /**
     * Calculates distance to each other node in the Apple Garden
     * on which hedgehog can collect max amount of apples
     *
     * @param source starting point zone
     */
    public void execute(AppleTree source) {
        settledNodes = new HashSet<>();
        unSettledNodes = new HashSet<>();
        distance = new HashMap<>();
        predecessors = new HashMap<>();
        distance.put(source, 0);
        unSettledNodes.add(source);
        while (unSettledNodes.size() > 0) {
            AppleTree node = getMinimum(unSettledNodes);
            settledNodes.add(node);
            unSettledNodes.remove(node);
            findMinimalDistances(node);
        }
    }

    /**
     * Calculates max number of apples which hedgehog can collect
     * on the the way to target Apple Tree
     *
     * @param target target Apple Tree
     * @return
     */
    public Integer getPath(AppleTree target) {
        Integer result = 0;
        AppleTree step = target;
        // check if a path exists
        if (predecessors.get(step) == null) {
            return null;
        }
        result += step.getValue();
        while (predecessors.get(step) != null) {
            step = predecessors.get(step);
            result += step.getValue();
        }
        return result;
    }

    private void findMinimalDistances(AppleTree node) {
        getNeighbors(node).stream()
                .filter(target -> getShortestDistance(target) > getShortestDistance(node) + target.getValue())
                .forEach(target -> {
                    distance.put(target, getShortestDistance(node) + target.getValue());
                    predecessors.put(target, node);
                    unSettledNodes.add(target);
                });
    }

    private List<AppleTree> getNeighbors(AppleTree node) {
        return steps.stream()
                .filter(edge -> edge.getSource().equals(node) && !isSettled(edge.getDestination()))
                .map(Step::getDestination)
                .collect(Collectors.toList());
    }

    private AppleTree getMinimum(Set<AppleTree> appleTrees) {
        AppleTree minimum = null;
        for (AppleTree appleTree : appleTrees) {
            if(minimum == null || getShortestDistance(appleTree) > getShortestDistance(minimum)) {
                minimum = appleTree;
            }
        }
        return minimum;
    }

    private boolean isSettled(AppleTree appleTree) {
        return settledNodes.contains(appleTree);
    }

    private int getShortestDistance(AppleTree destination) {
        Integer d = distance.get(destination);
        return d == null ? Integer.MAX_VALUE : d;
    }

}