public class LinkedListApp {
    public static void main(String[] args) {
        MyDoublyLinkedList<String> list = new MyDoublyLinkedList<>();

        list.addLast("A");
        list.addLast("B");
        list.addFirst("X");
        list.add(2, "C");  // Вставка в середину

        System.out.println(list);  // [X, A, C, B]
        System.out.println("Size: " + list.size());  // Size: 4
        System.out.println("Element at index 1: " + list.get(1));  // A

        list.remove(1);
        System.out.println(list);  // [X, C, B]
    }
}
