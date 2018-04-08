package src;

import java.util.Iterator;
import java.util.Optional;

public class CharList implements Iterable<Char> {

    public Char root = null; // First character of line
    public int size;
    public CharList(){
        size = 0;
        root = new Char("EMPTY");
    }
    public Iterator<Char> iterator() {
        return new CharIterator();
    }
    class CharIterator implements Iterator<Char> {
        private Char current = root;

        public boolean hasNext() {
            return current != null;
        }

        public Char next(){
            current = current.nextChar;
            return current;
        }

        public void remove(){

        }
    }
    public void addFirst(char c){
        Char temp = root.nextChar;
        root = new Char(c);
        root.nextChar = temp;
        size ++;
        System.out.print(c);
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
        added.prevChar = iterator.prevChar;
        added = iterator.prevChar;
        added.nextChar = iterator;
        size ++;
        System.out.print(c);
    }

    public int getSize() { return size; }

    public boolean isEmpty(){
        if (size == 0) return true;
        return false; 
    }
}