package ds;

public interface LinkedListInterface {
    public boolean empty();
    public int getSize();
    public NodeInterface getFirst();
    public NodeInterface getLast();
    public void insertBeginning(NodeInterface newNode);
    public void insertEnd(NodeInterface newNode);
    public void insert(NodeInterface newNode, int position);
    public void removeBeginning();
    public void removeEnd();
    public void remove(int position);
}
