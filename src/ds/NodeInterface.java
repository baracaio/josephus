package ds;

public interface NodeInterface {
    public boolean getValue();

    public NodeInterface getNext();

    public NodeInterface getPrevious();

    public void setNext(NodeInterface node);

    public void setPrevious(NodeInterface node);

    public void setValue(boolean state);
}