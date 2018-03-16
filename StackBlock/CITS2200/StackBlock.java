import CITS2200.*;

public class StackBlock implements Stack{

    private java.util.Stack<Object> block = new java.util.Stack<>();
    private int size;
    private int stackCount;

    public StackBlock(int s){
        size = s;
    }


    public boolean isEmpty(){
        return (stackCount == 0);
    }

    public boolean isFull(){
        return (stackCount == size);
    }

    public void push(Object o) throws Overflow{
        if(!isFull()){
            block.push(o);
            stackCount++;
        } else throw new Overflow("Stack is Full");
    }

    public Object examine() throws Underflow{
        if (!isEmpty()){
            return block.peek();
        } else throw new Underflow("Stack is Empty");

    }

    public Object pop() throws Underflow{
        if (!isEmpty()) {
            Object copy = block.peek();
            block.pop();
            stackCount--;
            return copy;
        } else throw new Underflow("Stack is empty");
    }

}
