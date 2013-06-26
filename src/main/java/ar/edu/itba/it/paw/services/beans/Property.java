package ar.edu.itba.it.paw.services.beans;

import java.util.List;

import ar.edu.itba.it.paw.services.ValidatorImpl;
import ar.edu.itba.it.paw.services.exceptions.BadInformationException;
import ar.edu.itba.it.paw.services.exceptions.InformationMissingException;
import ar.edu.itba.it.paw.services.interfaces.Validator;

public class Property {

	private int id;
	private String neighbourhood;
	private int coveredsurface;
	private int uncoveredsurface;
	private int rooms;
	private String description;
	private int age;
	private String street;
	private int numbering;
	private Integer floor;
	private String apartment = null;
	private int price;
	private String propertyType;
	private String operationType;
	private String status;
	private List<Service> services;
	private int userId;
	private Integer mainPhotoId = null;
	private Validator v = new ValidatorImpl();

	private final static int maxNeighbourhoodLenght = 50;
	private final static int maxDescriptionLenght = 512;
	private final static int maxStreetLenght = 50;
	private final static int maxApartmentLenght = 5;

	public Property(String neighbourhood, int coversup, int uncoversup,
			int rooms, String description, int age, String street,
			int numbering, Integer floor, int price, String apartment)
			throws InformationMissingException, BadInformationException {

		this.setNeighbourhood(neighbourhood);
		this.setCoveredsurface(coversup);
		this.setUncoveredsurface(uncoversup);
		this.setRooms(rooms);
		this.setDescription(description);
		this.setAge(age);
		this.setStreet(street);
		this.setNumbering(numbering);
		this.setFloor(floor);
		this.setPrice(price);
		this.setApartment(apartment);

	}

	public String getOperationType() {
		return operationType;
	}

	public void setOperationType(String operationType)
			throws InformationMissingException {
		if (operationType.equals(""))
			throw new InformationMissingException();
		this.operationType = operationType;
	}

	public String getPropertyType() {
		return propertyType;
	}

	public void setPropertyType(String propertyType)
			throws InformationMissingException {
		if (propertyType.equals(""))
			throw new InformationMissingException();
		this.propertyType = propertyType;
	}

	public int getId() {
		return id;
	}

	public String getNeighbourhood() {
		return neighbourhood;
	}

	public void setNeighbourhood(String neighbourhood)
			throws InformationMissingException, BadInformationException {

		if (!v.validateLenght(neighbourhood, maxNeighbourhoodLenght))
			throw new BadInformationException();

		if (!neighbourhood.equals(""))
			this.neighbourhood = neighbourhood;
		else
			throw new InformationMissingException();

	}

	public int getCoveredsurface() {
		return coveredsurface;
	}

	public void setCoveredsurface(int coveredsurface)
			throws BadInformationException {

		if (v.between(coveredsurface, 1, Integer.MAX_VALUE))
			this.coveredsurface = coveredsurface;
		else
			throw new BadInformationException();

	}

	public int getUncoveredsurface() {
		return uncoveredsurface;
	}

	public void setUncoveredsurface(int uncoveredsurface)
			throws BadInformationException {
		if (v.between(uncoveredsurface, 0, Integer.MAX_VALUE))
			this.uncoveredsurface = uncoveredsurface;
		else
			throw new BadInformationException();

	}

	public int getRooms() {
		return rooms;
	}

	public void setRooms(int rooms) throws BadInformationException {
		if (v.between(rooms, 0, Integer.MAX_VALUE))
			this.rooms = rooms;
		else
			throw new BadInformationException();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description)
			throws BadInformationException {
		if (v.validateLenght(description, maxDescriptionLenght))
			this.description = description;
		else
			throw new BadInformationException();
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) throws BadInformationException {
		if (v.between(age, 0, Integer.MAX_VALUE))
			this.age = age;
		else
			throw new BadInformationException();
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) throws InformationMissingException,
			BadInformationException {

		if (!v.validateLenght(street, maxStreetLenght))
			throw new BadInformationException();
		if (!street.equals(""))
			this.street = street;
		else
			throw new InformationMissingException();
	}

	public int getNumbering() {
		return numbering;
	}

	public void setNumbering(int numbering) throws BadInformationException {
		if (v.between(numbering, 0, Integer.MAX_VALUE))
			this.numbering = numbering;
		else
			throw new BadInformationException();
	}

	public Integer getFloor() {
		return floor;
	}

	public void setFloor(Integer floor) throws BadInformationException {
		if (floor != null) {
			if (!v.between(floor, 0, Integer.MAX_VALUE)) {
				throw new BadInformationException();
			} else
				this.floor = floor;
		}
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) throws BadInformationException {
		if (v.between(price, 0, Integer.MAX_VALUE))
			this.price = price;
		else
			throw new BadInformationException();
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Service> getServices() {
		return services;
	}

	public void setServices(List<Service> services) {
		this.services = services;
	}

	public String getApartment() {
		return apartment;
	}

	public void setApartment(String apartment) throws BadInformationException {
		if (apartment != null) {
			if (v.validateLenght(apartment, maxApartmentLenght))
				this.apartment = apartment;
			else
				throw new BadInformationException();
		}
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Integer getMainPhoto() {
		return mainPhotoId;
	}

	public void setMainPhotoId(Integer mainPhotoId) {
		this.mainPhotoId = mainPhotoId;
	}

}
