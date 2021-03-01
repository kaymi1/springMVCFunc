package ru.ivanov.dao;

import org.springframework.stereotype.Component;
import ru.ivanov.models.Person;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class PersonDAO {
    private List<Person> peopleDB;
    private static int ID;

    public PersonDAO(){
        peopleDB = new ArrayList<>();
        peopleDB.add(new Person(++ID, "Alexander"));
        peopleDB.add(new Person(++ID, "Anastasya"));
        peopleDB.add(new Person(++ID, "Alexsey"));
    }

    public List<Person> index(){
        return peopleDB;
    }

    public Person show(int id){
        return peopleDB.stream().filter(person -> person.getId() == id).findAny().orElse(null);
    }

    public void save(Person person) {
        person.setId(++ID);
        peopleDB.add(person);
    }
}
