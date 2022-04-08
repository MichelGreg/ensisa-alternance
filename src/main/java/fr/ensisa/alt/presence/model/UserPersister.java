package fr.ensisa.alt.presence.model;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import java.io.File;

public class UserPersister {

	private final JAXBContext jaxbContext;
	private final Marshaller marshaller;
	private final Unmarshaller unmarshaller;
	private final File storefile;

	public UserPersister(String filename) throws JAXBException {
		jaxbContext = JAXBContext.newInstance(User.class);

		marshaller = jaxbContext.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

		unmarshaller = jaxbContext.createUnmarshaller();

		storefile = new File(filename);
	}

	public void serialiseUser(User userToStore) throws JAXBException {
		marshaller.marshal(userToStore, storefile);
	}

	public User deserialiseUser() throws JAXBException {
		return (User) unmarshaller.unmarshal(storefile);
	}
}
