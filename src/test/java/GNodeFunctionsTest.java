import cisco.java.challenge.GNode;
import cisco.java.challenge.impl.GNodeImpl;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GNodeFunctionsTest {
    private GNodeImpl a = new GNodeImpl("A");
    private GNodeImpl b = new GNodeImpl("B");
    private GNodeImpl c = new GNodeImpl("C");
    private GNodeImpl c1 = new GNodeImpl("C");
    private GNodeImpl d = new GNodeImpl("D");
    private GNodeImpl e = new GNodeImpl("E");
    private GNodeImpl f = new GNodeImpl("F");
    private GNodeImpl g = new GNodeImpl("G");
    private GNodeImpl h = new GNodeImpl("H");
    private GNodeImpl i = new GNodeImpl("I");

    @Test
    public void walkGraphWithStream() {

        initNodeForWalk();

        ArrayList<GNode> nodeArrayList = GNodeFunctions.walkGraphWithStream(a);
        /*Amount of objects in the array list reduce from 7 to 6 due tree contains two equals Node with name 'C' */
        assertEquals("Size of arrayList should be equal 6", 6, nodeArrayList.size());
        assertTrue("arrayList should contains A", nodeArrayList.contains(a));
        assertTrue("arrayList should contains B", nodeArrayList.contains(b));
        assertTrue("arrayList should contains C", nodeArrayList.contains(c));
        assertTrue("arrayList should contains D", nodeArrayList.contains(d));
        assertTrue("arrayList should contains E", nodeArrayList.contains(e));
        assertTrue("arrayList should contains F", nodeArrayList.contains(f));

    }
    @Test
    public void walkGraph() {

        initNodeForWalk();

        ArrayList<GNode> nodeArrayList = GNodeFunctions.walkGraph(a);
        /*Amount of objects in the array list reduce from 7 to 6 due tree contains two equals Node with name 'C' */
        assertEquals("Size of arrayList should be equal 6", 6, nodeArrayList.size());
        assertTrue("arrayList should contains A", nodeArrayList.contains(a));
        assertTrue("arrayList should contains B", nodeArrayList.contains(b));
        assertTrue("arrayList should contains C", nodeArrayList.contains(c));
        assertTrue("arrayList should contains D", nodeArrayList.contains(d));
        assertTrue("arrayList should contains E", nodeArrayList.contains(e));
        assertTrue("arrayList should contains F", nodeArrayList.contains(f));
    }

    private void initNodeForWalk(){
        /*
        A
         ├── B
         │  ├── C
         │  └── D
         └── C
            ├── E
            └── F
        */
        a.setGNode(new GNode[]{b, c1});
        b.setGNode(new GNode[]{c, d});
        c1.setGNode(new GNode[]{e, f});
    }

    @Test
    public void paths() {

        initNodeForPath();

        ArrayList<ArrayList<GNode>> pathsNode = GNodeFunctions.paths(a);

        assertEquals("Size of arrayList should be equal 6", 6, pathsNode.size());
        /*result should contains ((A B E) (A B F) (A C G) (A C H) (A C I) (A D) )*/
        assertTrue("Array should contains path (A B E)", pathsNode.contains(Arrays.asList(a, b, e)));
        assertTrue("Array should contains path (A B F)", pathsNode.contains(Arrays.asList(a, b, f)));
        assertTrue("Array should contains path (A C G)", pathsNode.contains(Arrays.asList(a, c, g)));
        assertTrue("Array should contains path (A C H)", pathsNode.contains(Arrays.asList(a, c, h)));
        assertTrue("Array should contains path (A C I)", pathsNode.contains(Arrays.asList(a, c, i)));
        assertTrue("Array should contains path (A, D)", pathsNode.contains(Arrays.asList(a, d)));

    }

    @Test
    public void pathsWithStream() {
        initNodeForPath();

        ArrayList<ArrayList<GNode>> pathsNode = GNodeFunctions.pathsWithStream(a);
        //System.out.println(pathsNode);

        assertEquals("Size of arrayList should be equal 6", 6, pathsNode.size());
        /*result should contains ((A B E) (A B F) (A C G) (A C H) (A C I) (A D) )*/
        assertTrue("Array should contains path (A B E)", pathsNode.contains(Arrays.asList(a, b, e)));
        assertTrue("Array should contains path (A B F)", pathsNode.contains(Arrays.asList(a, b, f)));
        assertTrue("Array should contains path (A C G)", pathsNode.contains(Arrays.asList(a, c, g)));
        assertTrue("Array should contains path (A C H)", pathsNode.contains(Arrays.asList(a, c, h)));
        assertTrue("Array should contains path (A C I)", pathsNode.contains(Arrays.asList(a, c, i)));
        assertTrue("Array should contains path (A, D)", pathsNode.contains(Arrays.asList(a, d)));
    }

    private void initNodeForPath(){

        /*
        A
        ├── B
        │   ├── E
        │   └── F
        ├── C
        │   ├── G
        │   ├── H
        │   └── I
        └── D
        */
        a.setGNode(new GNode[]{b, c, d});
        b.setGNode(new GNode[]{e, f});
        c.setGNode(new GNode[]{g, h, i});
    }
}