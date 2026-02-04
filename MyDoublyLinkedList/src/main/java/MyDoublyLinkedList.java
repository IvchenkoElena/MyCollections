public class MyDoublyLinkedList<E> {
    private Node<E> head;
    private Node<E> tail;
    private int size;

    public MyDoublyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public void addFirst(E data) {
        Node<E> newNode = new Node<>(data);

        if (head == null) {
            // список пуст
            head = newNode;
            tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        size++;
    }

    public void addLast(E data) {
        Node<E> newNode = new Node<>(data);

        if (tail == null) {
            // список пуст
            head = newNode;
            tail = newNode;
        } else {
            newNode.prev = tail;
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        Node<E> current;

        // оптимизируем, с какой стороны начать
        if (index < size / 2) {
            current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
        } else {
            current = tail;
            for (int i = size - 1; i > index; i--) {
                current = current.prev;
            }
        }
        return current.data;
    }

    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        Node<E> toRemove;

        // оптимизируем, с какой стороны начать
        if (index < size / 2) {
            toRemove = head;
            for (int i = 0; i < index; i++) {
                toRemove = toRemove.next;
            }
        } else {
            toRemove = tail;
            for (int i = size - 1; i > index; i--) {
                toRemove = toRemove.prev;
            }
        }

        E removedData = toRemove.data;

        // обновляем ссылки
        if (toRemove.prev != null) {
            toRemove.prev.next = toRemove.next;
        } else {
            // удаляем голову
            head.next = toRemove.next;
        }

        if (toRemove.next != null) {
            toRemove.next.prev = toRemove.prev;
        } else {
            // удаляем хвост
            tail.prev = toRemove.prev;
        }

        size--;
        return removedData;
    }

    public void add(int index, E data) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        if (index == 0) {
            addFirst(data);
            return;
        }

        if (index == size) {
            addLast(data);
            return;
        }

        Node<E> toAdd = new Node<>(data);
        Node<E> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }

        toAdd.prev = current.prev;
        toAdd.next = current;
        current.prev.next = toAdd;
        current.prev = toAdd;

        size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");

        Node<E> current = head;
        while (current != null) {
            sb.append(current.data);
            if (current.next != null) {
                sb.append(", ");
            }
            current = current.next;
        }
        sb.append("]");

        return sb.toString();
    }
}
