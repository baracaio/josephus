package ds;

public class Player implements NodeInterface {

    private NodeInterface next;

    private NodeInterface previous;

    private boolean value;

    public Player() {
        next = null;
        previous = null;
        value = true;
    }

    @Override
    public NodeInterface getNext() {
        return next;
    }

    @Override
    public NodeInterface getPrevious() {
        return previous;
    }

    @Override
    public void setNext(NodeInterface node) {
        next = node;
    }

    @Override
    public void setPrevious(NodeInterface node) {
        previous = node;
    }

    @Override
    public boolean getValue() {
        return value;
    }

    @Override
    public void setValue(boolean state) {
        value = state;
    }
}
