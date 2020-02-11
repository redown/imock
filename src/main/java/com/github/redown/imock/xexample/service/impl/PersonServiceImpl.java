package com.github.redown.imock.xexample.service.impl;

import com.github.redown.imock.xexample.entity.PersonInput;
import com.github.redown.imock.xexample.entity.PersonOutput;
import com.github.redown.imock.xexample.service.PersonService;

/**
 * @author redown
 */
public class PersonServiceImpl implements PersonService {

    @Override
    public PersonOutput addPerson(PersonInput input) {
        PersonOutput output = new PersonOutput();
        output.setName(input.getName());
        output.setAge(input.getAge());
        return output;
    }

    @Override
    public PersonOutput queryPerson(PersonInput input) {
        PersonOutput output = new PersonOutput();
        output.setName(input.getName());
        output.setAge(input.getAge());
        return output;
    }
}
