package com.baguio.googlecontactactivity.service;

import com.google.api.services.people.v1.PeopleService;
import com.google.api.services.people.v1.model.*;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.List;

@Service
public class GoogleContactService {
    private final PeopleService peopleService;

    public GoogleContactService(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    public List<Person> getAllContacts() throws IOException {
        ListConnectionsResponse response = peopleService.people().connections()
                .list("people/me")
                .setPersonFields("names,emailAddresses,phoneNumbers")
                .execute();
        return response.getConnections();
    }

    public void addContact(Person person) throws IOException {
        peopleService.people().createContact(person).execute();
    }

    public void updateContact(String resourceName, Person person) throws IOException {
        peopleService.people().updateContact(resourceName, person).execute();
    }

    public void deleteContact(String resourceName) throws IOException {
        peopleService.people().deleteContact(resourceName).execute();
    }
}
