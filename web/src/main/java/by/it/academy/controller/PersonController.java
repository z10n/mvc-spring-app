/*
 * Copyright (C) 2015 GHX, Inc.
 *  Louisville, Colorado, USA.
 *  All rights reserved.
 *
 *  Warning: Unauthorized reproduction or distribution of this program, or
 *  any portion of it, may result in severe civil and criminal penalties,
 *  and will be prosecuted to the maximum extent possible under the law.
 *
 *  Created on 012 12.01.2015
 */
package by.it.academy.controller;

import by.it.academy.pojos.Person;
import by.it.academy.services.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/person")
public class PersonController {

    @Autowired(required = true)
    private IPersonService personService;

    @RequestMapping(value = "/persons", method = RequestMethod.GET)
    public String mainPage(ModelMap model) {
        fillModel(model);
        return "persons/main";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addPerson(ModelMap model, Person person) {
        if (person != null) {
            person = personService.create(person);
            model.put("person", person);
        }
        model.put("persons", personService.getPersons());
        return "persons/main";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String deletePerson(ModelMap model, Person person) {
        if (person != null) {
            personService.delete(person);
            model.put("message", "Person: " + person.getName() + " was deleted");
        }
        fillModel(model);

        return "persons/main";
    }

    private void fillModel(ModelMap model) {
        List<Person> list = personService.getPersons();
        model.put("persons", list);
        Person person = new Person();
        if (list.size() > 1) {
            person = list.get(0);
        }
        model.put("person", person);
    }

}
