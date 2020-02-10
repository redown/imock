package com.github.redown.imock.xexample.service;

import com.github.redown.imock.api.IApi;
import com.github.redown.imock.api.IApiMethod;
import com.github.redown.imock.xexample.entity.PersonInput;
import com.github.redown.imock.xexample.entity.PersonOutput;

/**
 * @author redown
 */
@IApi(name = "Person Service")
public interface PersonService {
    @IApiMethod(name = "addPerson")
    PersonOutput addPerson(PersonInput input);

    @IApiMethod(name = "qaueryPerson")
    PersonOutput queryPerson(PersonInput input);
}
