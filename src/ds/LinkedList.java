package ds;

public class LinkedList implements LinkedListInterface {
    private NodeInterface first;
    private NodeInterface last;
    private int size;

    public LinkedList() {
        first = null;
        last = null;
        size = 0;
    }

    @Override
    public boolean empty() {
        return size == 0;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public NodeInterface getFirst() {
        return first;
    }

    @Override
    public NodeInterface getLast() {
        return last;
    }

    @Override
    public void insertBeginning(NodeInterface node) {
        if (size == 0) {
            first = node;
            last = node;
            first.setNext(last);
            last.setPrevious(first);
            size++;
            return;
        }

        node.setNext(first);
        first = node;
        size++;
    }

    @Override
    public void insertEnd(NodeInterface node) {
        if (size == 0) {
            first = node;
            last = node;
            first.setNext(last);
            last.setPrevious(first);
            size++;
            return;
        }

        node.setPrevious(last);
        last = node;
        size++;
    }

    @Override
    public void insert(NodeInterface newNode, int position) {
        if (position > 0 && position <= size) {
            NodeInterface node = first;
            NodeInterface previous;

            for (int i = 1; i <= position; i++) {
                node = node.getNext();
            }

            previous = node.getPrevious();
            previous.setNext(newNode);

            newNode.setPrevious(previous);
            newNode.setNext(node);

            node.setPrevious(newNode);

            size++;
        }
    }

    @Override
    public void removeBeginning() {
        if (size <= 1) {
            first = null;
            last = null;
            size = 0;
            return;
        }

        first = first.getNext();
        first.setPrevious(last);
        last.setNext(first);

        size--;
    }

    @Override
    public void removeEnd() {
        if (size <= 1) {
            first = null;
            last = null;
            size = 0;
            return;
        }

        last = last.getPrevious();
        last.setNext(first);
        first.setPrevious(last);

        size--;
    }

    @Override
    public void remove(int position) {
        if (position > size || position < 1) {
            return;
        }

        NodeInterface previous;
        NodeInterface next;
        NodeInterface node = first;

        for (int i = 1; i <= position; i++) {
            node = node.getNext();
        }

        previous = node.getPrevious();
        next = node.getNext();

        next.setPrevious(previous);
        previous.setNext(next);

        node = null;
        size--;
    }
}