public class MySimpleHashMap<Key, Value> {
    private EntryPair<Key, Value>[] table; // массив ячеек с первой парой в цепочке
    private static final int DEFAULT_CAPASITY = 8; // начальный размер массива
    private static final float LOAD_FACTOR = 0.75f; // коэффициент загрузки
    private int size; // количество записанных элементов
    private int treashold; // пороговое значение (размер массива * коэффициент загрузки)

    public MySimpleHashMap() {
        table = new EntryPair[DEFAULT_CAPASITY]; // создаем новый массив дефолтного размера
        size = 0;
        treashold = (int) (DEFAULT_CAPASITY * LOAD_FACTOR);
    }

    // хэш-функция для определения индекса ячейки
    private int getBucketIndex(Key key) {
        return Math.abs(key.hashCode()) % table.length;
    }

    private int getIndexForNewTable(Key key, int newCapasity) {
        return Math.abs(key.hashCode()) % newCapasity;
    }

    // функция эквивалентности ключей
    private boolean keysEqual(Key key1, Key key2) {
        return (key1 == key2) || (key1 != null && key1.equals(key2));
    }

    // метод расширения таблицы (в 2 раза)
    private void resize() {
        int newCapasity = table.length * 2;
        EntryPair<Key, Value>[] newTable = new EntryPair[newCapasity];

        // обновляем порог для новой ёмкости
        treashold = (int) (newCapasity * LOAD_FACTOR);

        // перехэшируем все элементы из старой таблицы с учетом емкости новой
        for (EntryPair<Key, Value> oldEntryPair : table) {
            while (oldEntryPair != null) {
                Key key = oldEntryPair.key;
                int newIndex = getIndexForNewTable(key, newCapasity);

                // вставляем в новую таблицу в начало цепочки
                EntryPair<Key, Value> temp = oldEntryPair.next;
                oldEntryPair.next = newTable[newIndex];
                newTable[newIndex] = oldEntryPair;

                // переходим к следующей паре в старой цепочке
                oldEntryPair = temp;
            }
        }
        // заменяем старую таблицу на новую
        table = newTable;
    }

    // метод вставки новой пары ключ-значение с проверкой на необходимость расширения
    public Value put(Key key, Value value) {
        // проверяем, необходимо ли расширение
        if (size >= treashold) {
            resize();
        }

        int index = getBucketIndex(key); // находим индекс ячейки по хэшу ключа

        EntryPair<Key, Value> entryPair = table[index]; // получаем первую пару в цепочке

        // проходим по цепочке, сравнивая ключи
        while(entryPair != null) {
            if(keysEqual(key, entryPair.key)) {
                // если ключ найден, обновляем значение
                entryPair.value = value;
                return value;
            }
            entryPair = entryPair.next;
        }
        // если пары с таким ключом в цепочке нет, записываем новую пару в начало цепочки
        EntryPair<Key, Value> newEntryPair = new EntryPair<>(key, value);
        newEntryPair.next = table[index];
        table[index] = newEntryPair;
        size++; // увеличиваем размер

        return value;
    }

    // метод поиска значания по ключу
    public Value get(Key key) {
        int index = getBucketIndex(key); // находим индекс ячейки по хэшу ключа

        EntryPair<Key, Value> entryPair = table[index]; // получаем первую пару в цепочке

        // проходим по цепочке, сравнивая ключи
        while(entryPair != null) {
            if(keysEqual(key, entryPair.key)) {
                // если ключ найден, возвращаем значение
                return entryPair.value;
            }
            entryPair = entryPair.next;
        }
        // если пары с таким ключом в цепочке нет, возвращаем null
        return null;
    }

    // метод удаления пары по ключу
    public Value remove(Key key) {
        int index = getBucketIndex(key); // находим индекс ячейки по хэшу ключа

        EntryPair<Key, Value> entryPair = table[index]; // получаем первую пару в цепочке
        EntryPair<Key, Value> prev = null;

        // проходим по цепочке, сравнивая ключи
        while(entryPair != null) {
            if(keysEqual(key, entryPair.key)) {
                // если ключ найден, удаляем пару и возвращаем удаленное значение
                Value oldValue = entryPair.value;
                if(prev == null) {
                    // если надо удалить первую пару в цепочке
                    table[index] = entryPair.next;
                } else {
                    // если удаляем из середины или конца
                    prev.next = entryPair.next;
                }
                size--;
                return oldValue;
            }
            prev = entryPair;
            entryPair = entryPair.next;
        }
        // если пары с таким ключом в цепочке нет, возвращаем null
        return null;
    }

    // метод определения количества записанных элементов
    public int size() {
        return size;
    }

    // метод определения пустой таблицы
    public boolean isEmpty() {
        return size == 0;
    }

    // метод определения наличия ключа
    public boolean containsKey(Key key) {
        return get(key) != null;
    }
}
