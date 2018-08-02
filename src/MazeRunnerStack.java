import java.util.EmptyStackException;

public class MazeRunnerStack implements StackADT<Position> {

	public Position[] storage = new Position[100];
	private int size = 0;
  
    @Override
    public void push(Position item) throws IllegalArgumentException {
    	if(item == null) {
        	throw new IllegalArgumentException();
        }
    	storage[size] = item;
    	size++;
    }

    @Override
    public Position pop() throws EmptyStackException {
    	if(size == 0) {
        	throw new EmptyStackException();
        }
    	Position popped = storage[size];
    	storage[size] = null;
 
    	size--;
        return popped;
    }

    @Override
    public Position peek() throws EmptyStackException {
        if(size == 0) {
        	throw new EmptyStackException();
        }
        return storage[size];
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
    	for (int i = size; i >= 0; i--) {
    		if (p == storage[i]) {
    			return true;
    		}
    	}
        return false;
    }
}

