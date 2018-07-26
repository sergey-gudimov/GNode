package cisco.java.challenge.impl;

import cisco.java.challenge.GNode;
import java.util.Objects;

public class GNodeImpl implements GNode {

    private String name;

    private GNode[] gNode = new GNode[0];

    public GNodeImpl(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public GNode[] getChildren() {
        return gNode;
    }

   public void setGNode(GNode[] gNode) {
        if (gNode != null) {
            this.gNode = gNode;
        } else {
            this.gNode = new GNode[0];
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof GNode)) {
            return false;
        }
        GNodeImpl gNode = (GNodeImpl) o;
        return Objects.equals(name, gNode.name);
    }

    @Override
    public String toString() {
        return "'" + name + "'";
    }
}
