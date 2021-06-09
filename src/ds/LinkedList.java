package ds;

public class LinkedList implements LinkedListInterface {
    private NodeInterface first;
    private NodeInterface last;
    private int size;

    @Override
    public boolean empty() {
        return size == 0;
    }

    @Override
    public void insertBeginning(NodeInterface node) {
        if (size == 0) {

        }
    }

    @Override
    public void insertEnd(NodeInterface node) {

    }

    @Override
    public void removeBeginning() {

    }

    @Override
    public void removeEnd() {

    }

    @Override
    public void remove(int position) {

    }
}