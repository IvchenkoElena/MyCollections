public class MyHashMapApp {
    // проверка работы хэш таблицы
    public static void main(String[] args) {
        MySimpleHashMap<String, Long> phoneNumbers = new MySimpleHashMap<>();

        System.out.println("Записная книжка пуста - " + phoneNumbers.isEmpty());

        phoneNumbers.put("Мама", 9088888888L);
        phoneNumbers.put("Папа", 9099999999L);
        phoneNumbers.put("Скорая", 003L);

        System.out.println("Теперь записная книжка не пуста - " + !phoneNumbers.isEmpty());
        System.out.println("Количество записей в книжке: " + phoneNumbers.size());

        System.out.println("В внижке есть Мама? - " + phoneNumbers.containsKey("Мама"));
        System.out.println("Её номер: " + phoneNumbers.get("Мама"));
        phoneNumbers.put("Мама", 9088765432L);
        System.out.println("Она сменила номер на: " + phoneNumbers.get("Мама"));

        phoneNumbers.remove("Скорая");
        System.out.println("Скорая нам не понадобится, количество записей: " + phoneNumbers.size());

    }
}
