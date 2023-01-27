public class Stack4 {
    int top;
    int size;
    Integer [] stack;

    public Stack4(int size) {
        this.size = size; //
        stack = new Integer[size];
        top = -1; // top 의 값 초기화
    }

    private void push(int item) {
        stack[++top] = item;
        System.out.println(stack[top] + " push!");
    }

    private void peek() {
        System.out.println(stack[top] + " peek!");
    }

    private void pop() {
        System.out.println(stack[top] + " pop!");
        stack[top--] = 0;
    }

    private int search(int item) {
        for (int i = 0; i <= top; i++) { // for 문은 top 만큼
            if (stack[i] == item)
                return (top - i) + 1; // top - 탐색한 배열의 인덱스, 배열 이므로 +1 추가
        }
        return -1;
    }

    public void clear() {
        for(int i = 0; i < top+1; i++) {
            stack[i] = null;
        }
        top = -1;
    }

    private boolean empty() {
        return size == 0;
    }

    private boolean contains(int item) {
        for(int s : stack) {
            if(s == item) return true;
        }
        return false;
    }


    public static void main(String[] args) {
        Stack4 stack = new Stack4(4);

        stack.push(1);      // Stack에 1 삽입
        stack.push(2);      // Stack에 2 삽입

        stack.pop();        // 상단 요소 제거(2)
        stack.pop();        // 최상위 요소 제거(1)

        stack.push(3);      // Stack에 3 삽입
        stack.push(4);      // Stack에 3 삽입


        stack.pop();        // 최상위 요소 제거(3)
        stack.pop();

        // Stack이 비어 있는지 확인

    }
}
