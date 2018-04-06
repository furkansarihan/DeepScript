package src;

public class CharList{

    public Char root = null; // First character of line
    public int size = null;
    public CharList(){
        size = 0;
    }

    public void addFirst(char c){
        Char temp = root.nextChar;
        root = new Char(c);
        root.nextChar = temp;
        size ++;
    }

    public void addLast(char c){
        Char iterator = root;
        while (iterator.nextChar == null)
            iterator = iterator.nextChar;
        iterator.nextChar = new Char(c);
        size ++;
    }   

    public void addSomeWhere(char c, int index){
        Char iterator = root;
        int i = 0;
        while (i != index) {
            iterator = iterator.nextChar;
            i++;
        }
        Char added = new Char(c);
        iterator.prevChar.nextChar = added;
        iterator.nextChar = added.prev;
        added.nextChar = iterator;
        size ++;
    }

    public int getSize() { return size; }

    public boolean isEmpty(){
        if (size == 0) return true;
        return false; 
    }
}