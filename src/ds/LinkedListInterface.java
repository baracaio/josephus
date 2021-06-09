package ds;

public interface LinkedListInterface {
    public boolean empty();
    public void insertBeginning(NodeInterface node);
    public void insertEnd(NodeInterface node);
    public void removeBeginning();
    public void removeEnd();
    public void remove(int position);
}
