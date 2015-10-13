package org.novoty.mapper;

import org.junit.Assert;
import org.junit.Test;
import org.novoty.mapper.test.Person;
import org.novoty.mapper.test.PersonDto;

public class ConfigurableMapperTest {

    private PersonMapper personMapper = new PersonMapper();

    @Test
    public void shouldCopyFieldsToNewPerson() throws Exception {
        Person origin = new Person();
        origin.setName("Original Origin");
        origin.setAge(10);

        PersonDto copy = new PersonDto();
        personMapper.map(origin, copy);

        Assert.assertEquals("name", copy.getName(), origin.getName());
        Assert.assertEquals("age", copy.getAge(), origin.getAge());
    }

    static class PersonMapper extends ConfigurableMapper<Person, PersonDto> {
        public PersonMapper() {
            map("name", Person::getName, PersonDto::setName);
            map("age", Person::getAge, PersonDto::setAge);
        }
    }
}