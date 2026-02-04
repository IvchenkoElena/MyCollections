
// класс для хранения связки данных ключ-значение
class EntryPair<Key, Value> {
    final Key key;
    Value value;
    EntryPair<Key,Value> next; // хранит цепочку пар ключ-значение для разрешения коллизий

    public EntryPair(Key key, Value value) {
        this.key = key;
        this.value = value;
    }
}
