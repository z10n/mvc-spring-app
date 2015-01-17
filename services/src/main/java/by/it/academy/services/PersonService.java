/*
 * Copyright (C) 2014 GHX, Inc.
 *  Louisville, Colorado, USA.
 *  All rights reserved.
 *
 *  Warning: Unauthorized reproduction or distribution of this program, or
 *  any portion of it, may result in severe civil and criminal penalties,
 *  and will be prosecuted to the maximum extent possible under the law.
 *
 *  Created on 009 09.09.2014
 */
package by.it.academy.services;

import by.it.academy.dao.PersonDao;
import by.it.academy.pojos.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

import java.util.List;

@Service
@org.springframework.transaction.annotation.Transactional(propagation = Propagation.REQUIRED)
public class PersonService implements IPersonService{

    @Autowired
    PersonDao personDao;

    public List<Person> getPersons() {
        return personDao.getPersons();
    }

    public Person create(Person person) {
        if(person != null) {
            return personDao.add(person);
        }
        return person;
    }

    public void delete(Person person) {
        if (person != null) {
            personDao.delete(person);
        }
    }
}
