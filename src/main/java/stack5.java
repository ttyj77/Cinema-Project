public class Stack {
    private int arr[];
    private int top;
    private int capacity;

    // Stack 초기화를 위한 생성자
    Stack(int size) {
        arr = new int[size];
        capacity = size;
        top = -1;
    }

    // Stack에 요소`x`를 추가하는 유틸리티 함수
    public void push(int x) {
        if (isFull()) {
            System.exit(-1);
        }
        arr[++top] = x;
    }

    // Stack에서 최상위 요소를 꺼내는 유틸리티 함수
    public int pop() {
// Stack 언더플로 확인
        if (isEmpty()) {
            System.exit(-1);
        }

// Stack 크기를1로 줄이고(선택적으로) 팝된 요소를 반환합니다.
        return arr[top--];
    }

    // Stack의 최상위 요소를 반환하는 유틸리티 함수
    public int peek() {
        if (!isEmpty()) {
            return arr[top];
        } else {
            System.exit(-1);
        }

        return -1;
    }

    public int size() {
        return top + 1;
    }

    public boolean isEmpty() {
        return top == -1; // or return size() == 0;
    }

    public boolean isFull() {
        return top == capacity - 1; // or return size() == capacity;
    }

    public static void main(String[] args) {
        Stack2 stack = new Stack2(4);

        stack.push(1); // Stack에1 삽입
        stack.push(2); // Stack에2 삽입

        stack.pop(); // 상단 요소 제거(2)
        stack.pop(); // 최상위 요소 제거(1)

        stack.push(3); // Stack에3 삽입
        stack.push(4); // Stack에3 삽입

        System.out.println("The top element is " + stack.peek());
        System.out.println("The stack size is " + stack.size());

        stack.pop(); // 최상위 요소 제거(3)
        stack.pop();

        // Stack이 비어 있는지 확인
        if (stack.isEmpty()) {
            System.out.println("The stack is empty");
        } else {
            System.out.println("The stack is not empty");
        }
    }
}

