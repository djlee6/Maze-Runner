import java.util.EmptyStackException;

public class MazeRunnerStack implements StackADT<Position> {
    private int size = 0;
    private Position top;
    private Position[] stackArray = new Position[100];
    private Position previous;
    
    @Override
    public void push(Position item) throws IllegalArgumentException {
        if(item == null) {
            throw new IllegalArgumentException("Trying to add a null item to stack.");
        }
        if(isEmpty()) {
            top = item;
            stackArray[0] = top;
            size++;
        }
        else {
            size++;
            top = item;
            for(int i = 0; i < size; i++) {
                stackArray[i + 1] = stackArray[i];
            }
            stackArray[0] = top;
        }
    }

    @Override
    public Position pop() throws EmptyStackException {
        if(isEmpty()) {
            throw new EmptyStackException();
        }
        else {
            previous = top;
            size--;
            for(int i = 0; i < size; i++) {
                stackArray[i] = stackArray[i + 1];
            }
            top = stackArray[0];
            return previous;
        }
    }

    @Override
    public Position peek() throws EmptyStackException {
        if(isEmpty()) {
            throw new EmptyStackException();
        }
        else {
            return top;
        }
    }

    @Override
    public boolean isEmpty() {
        if(size == 0) {
            return true;
        }
        else {
            return false;
        }
    }
    public boolean contains(Position p) { //Reports whether the Position p can be found within the stack
        for(int i = 0; i < size; i++) {
            if(stackArray[i].equals(p)) {
                return true;
            }
            else {
                continue;
            }
        }
        return false;
    }
}