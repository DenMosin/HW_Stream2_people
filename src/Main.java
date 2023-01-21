import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {

        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
        Stream<Person> stream = persons.stream();

//         Найти количество несовершеннолетних

        long age = persons.stream()
                .filter(a -> a.getAge() >= 18)
                .count();
//        System.out.println(age);

//         Получить список фамилий призывников (т.е. мужчин от 18 и до 27 лет)

       List<String> list = persons.stream()
                .filter(a -> a.getSex().equals(Sex.MAN))
                .filter(a -> a.getAge() >= 0 && a.getAge() <= 27)
                .map(person -> person.getFamily())
                .collect(Collectors.toList());
//        System.out.println(list);

//         Получить отсортированный по фамилии список потенциально работоспособных людей с
//         высшим образованием в выборке (т.е. людей с высшим образованием от 18 до 60 лет для женщин
//         и до 65 лет для мужчин).

        List<String> list1 = persons.stream()
                .filter(x -> x.getEducation().equals(Education.HIGHER))
                .filter(x -> x.getSex().equals(Sex.WOMAN) && x.getAge() < 60 && x.getAge() >=18
                        || x.getSex().equals(Sex.MAN) && x.getAge() < 65 && x.getAge() >=18)
                .sorted(Comparator.comparing(Person::getFamily))
                .map(person -> person.toString())
                .collect(Collectors.toList());
//      System.out.println(list1);
    }
}