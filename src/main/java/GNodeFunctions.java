import cisco.java.challenge.GNode;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GNodeFunctions {

    /*Function returns an ArrayList containing every GNode without duplicates in the graph gNode.*/
    public static ArrayList<GNode> walkGraphWithStream(GNode gNode) {
        return GNodeFunctions.streamWalk(gNode).distinct().collect(Collectors.toCollection(ArrayList::new));
    }

    /*Function merge node with children nodes*/
    private static Stream<GNode> streamWalk(GNode gNode) {
        return Stream.concat(Stream.of(gNode),
                Stream.of(gNode.getChildren()).flatMap(GNodeFunctions::streamWalk));
    }

    /*Function return all possible paths through the graph starting at gNode*/
    public static ArrayList<ArrayList<GNode>> pathsWithStream(GNode gNode) {
        return addNextLevelNode(new ArrayList<>(Collections.singletonList(gNode))).collect(Collectors.toCollection(ArrayList::new));
    }


    /*Function add all next level to list for @listNodes*/
    private static Stream<ArrayList<GNode>> addNextLevelNode(ArrayList<GNode> listNode) {

        GNode[] children = listNode.get(listNode.size() - 1).getChildren();

        if (children.length == 0) {
            return Stream.of(listNode);
        }

        return Arrays.stream(children)
                .map(n -> {
                    ArrayList<GNode> arrayList = new ArrayList<>(listNode);
                    arrayList.add(n);
                    return arrayList;
                }).flatMap(GNodeFunctions::addNextLevelNode);
    }

    /*Function returns an ArrayList containing every GNode without duplicates in the graph gNode.*/
    public static ArrayList<GNode> walkGraph(GNode gNode) {

        Map<Integer, ArrayList<GNode>> nodeMap = new HashMap<>();
        int level = 0;
        nodeMap.put(level, new ArrayList<>(Collections.singletonList(gNode)));
        boolean isAddLevel = gNode.getChildren().length > 0;

        while (isAddLevel) {
            isAddLevel = false;
            ArrayList<GNode> listNode = new ArrayList<>();
            for (GNode node : nodeMap.get(level)) {
                listNode.addAll(Arrays.asList(node.getChildren()));
                isAddLevel = true;
            }

            nodeMap.put(++level, listNode);
        }

        return nodeMap.values().stream()
                .flatMap(ArrayList::stream)
                .distinct()
                .collect(Collectors.toCollection(ArrayList::new));


    }

    /*Function return all possible paths through the graph starting at gNode*/
    public static ArrayList<ArrayList<GNode>> paths(GNode gNode) {

        boolean isAddedNode;
        CopyOnWriteArrayList<ArrayList<GNode>> arrayLists = new CopyOnWriteArrayList<>();
        ArrayList<GNode> arrayList = new ArrayList<>();
        arrayList.add(gNode);
        arrayLists.add(arrayList);
        isAddedNode = gNode.getChildren().length > 0;
        while (isAddedNode) {
            isAddedNode = false;
            for (ArrayList<GNode> arrayPath : arrayLists) {
                isAddedNode = addPathNode(arrayLists, arrayPath);
            }

        }
        return new ArrayList<>(arrayLists);
    }

    /*Function add all existing path for next level children nodes*/
    private static boolean addPathNode(CopyOnWriteArrayList<ArrayList<GNode>> arrayLists, ArrayList<GNode> arrayPath) {
        boolean isAddedNode = false;

        GNode[] children = arrayPath.get(arrayPath.size() - 1).getChildren();
        for (GNode child : children) {
            ArrayList<GNode> addArrayPath = new ArrayList<>(arrayPath);
            addArrayPath.add(child);
            arrayLists.add(addArrayPath);
            arrayLists.remove(arrayPath);
            isAddedNode = true;
        }
        return isAddedNode;
    }


}
