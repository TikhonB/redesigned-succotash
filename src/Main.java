import java.util.*;
import java.util.stream.Collectors;

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
        long count = persons.stream() // первый метод вывода кол-ва несовершеннолетних
                .filter(x -> x.getAge() < 18)
                .count();
        System.out.println(count);

        List<String> conscript = persons.stream() // второй метод получения призывников
                .filter(age -> age.getAge() > 18 && age.getAge() < 27)
                .filter(sex -> sex.getSex() != Sex.WOMAN)
                .map(value -> value.getFamily())
                .collect(Collectors.toList());
        System.out.println(conscript);

        List<Person> performance = persons.stream() // третий метод на вывод работоспособных людей
                .filter(x -> x.getEducation() == Education.HIGHER)
                .filter(y -> y.getAge() > 18)
                .filter(z -> z.getSex() == Sex.MAN ?
                        z.getAge() < 65 :
                        z.getAge() < 60)
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());
        System.out.println(performance);



    }
}
