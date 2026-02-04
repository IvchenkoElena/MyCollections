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

        phoneNumbers.put("Друг1", 9081111111L);
        phoneNumbers.put("Друг2", 9082222222L);
        phoneNumbers.put("Друг3", 9083333333L);
        phoneNumbers.put("Друг4", 9084444444L);
        phoneNumbers.put("Друг5", 9085555555L);
        phoneNumbers.put("Друг6", 9086666666L);
        phoneNumbers.put("Друг7", 9087777777L);
        System.out.println("Таблица расширена. Количество записей: " + phoneNumbers.size());






    }
}
