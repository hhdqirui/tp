package seedu.address.storage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.core.index.exceptions.InvalidIndexException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.location.Location;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.InfectionStatus;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.QuarantineStatus;
import seedu.address.model.tag.Tag;
import seedu.address.model.visit.Visit;

/**
 * Jackson-friendly version of {@link Visit}.
 */
public class JsonAdaptedVisit {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Visit's %s field is missing!";

    private final String namePerson;
    private final String phone;
    private final String email;
    private final String addressPerson;
    private final String quarantineStatus;
    private final String infectionStatus;
    private final String idPerson;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();
    private final String nameLocation;
    private final String addressLocation;
    private final String idLocation;
    private final String dateOfVisit;

    /**
     * Constructs a {@code JsonAdaptedVisit} with the given visit details.
     */
    @JsonCreator
    public JsonAdaptedVisit(@JsonProperty("namePerson") String namePerson,
                            @JsonProperty("phone") String phone,
                            @JsonProperty("email") String email,
                            @JsonProperty("addressPerson") String addressPerson,
                            @JsonProperty("quarantineStatus") String quarantineStatus,
                            @JsonProperty("infectionStatus") String infectionStatus,
                            @JsonProperty("idPerson") String idPerson,
                            @JsonProperty("tagged") List<JsonAdaptedTag> tagged,
                            @JsonProperty("nameLocation") String nameLocation,
                            @JsonProperty("addressLocation") String addressLocation,
                            @JsonProperty("idLocation") String idLocation,
                            @JsonProperty("dateOfVisit") String date) {
        this.namePerson = namePerson;
        this.phone = phone;
        this.email = email;
        this.addressPerson = addressPerson;
        this.quarantineStatus = quarantineStatus;
        this.infectionStatus = infectionStatus;
        this.idPerson = idPerson;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
        this.nameLocation = nameLocation;
        this.addressLocation = addressLocation;
        this.idLocation = idLocation;
        this.dateOfVisit = date;
    }

    /**
     * Converts a given {@code Visit} into this class for Jackson use.
     */
    public JsonAdaptedVisit(Visit source) {
        namePerson = source.getPerson().getName().fullName;
        phone = source.getPerson().getPhone().value;
        email = source.getPerson().getEmail().value;
        addressPerson = source.getPerson().getAddress().value;
        quarantineStatus = source.getPerson().getQuarantineStatus().value;
        infectionStatus = source.getPerson().getInfectionStatus().getStatusAsString();
        idPerson = source.getPerson().getId().toString();
        tagged.addAll(source.getPerson().getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        nameLocation = source.getLocation().getName().fullName;
        addressLocation = source.getLocation().getAddress().value;
        idLocation = source.getLocation().getId().toString();
        dateOfVisit = source.getDate().toString();
    }

    /**
     * Converts this Jackson-friendly adapted visit object into the model's {@code Visit} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted visit.
     */
    public Visit toModelType() throws IllegalValueException {
        final List<Tag> personTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            personTags.add(tag.toModelType());
        }

        if (namePerson == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(namePerson)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(namePerson);

        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelPhone = new Phone(phone);

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        if (addressPerson == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(addressPerson)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Address modelAddress = new Address(addressPerson);

        if (quarantineStatus == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, QuarantineStatus.class.getSimpleName()));
        }
        if (!QuarantineStatus.isValidQuarantineStatus(quarantineStatus)) {
            throw new IllegalValueException(QuarantineStatus.MESSAGE_CONSTRAINTS);
        }
        final QuarantineStatus modelQuarantineStatus = new QuarantineStatus(quarantineStatus);

        if (infectionStatus == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, InfectionStatus.class.getSimpleName())
            );
        }
        if (!InfectionStatus.isValidInfectionStatus(infectionStatus)) {
            throw new IllegalValueException(InfectionStatus.MESSAGE_CONSTRAINTS);
        }
        final InfectionStatus modelInfectionStatus = new InfectionStatus(infectionStatus);

        if (idPerson == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, "id")
            );
        }
        final Index modelId;
        try {
            modelId = Index.fromOneBased(Integer.parseInt(idPerson));
        } catch (ClassCastException | IndexOutOfBoundsException e) {
            throw new InvalidIndexException();
        }

        final Set<Tag> modelTags = new HashSet<>(personTags);
        final Person modelPerson = new Person(modelName, modelPhone, modelEmail, modelAddress, modelQuarantineStatus,
                modelInfectionStatus, modelId, modelTags);

        if (nameLocation == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(nameLocation)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelNameLocation = new Name(nameLocation);

        if (addressLocation == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(addressLocation)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Address modelAddressLocation = new Address(addressLocation);

        if (idLocation == null) {
            throw new IllegalValueException(MISSING_FIELD_MESSAGE_FORMAT);
        }
        final Index modelIdLocation;
        try {
            modelIdLocation = Index.fromOneBased(Integer.parseInt(idLocation));
        } catch (ClassCastException | IndexOutOfBoundsException e) {
            throw new InvalidIndexException();
        }

        final Location modelLocation = new Location(modelNameLocation, modelAddressLocation, modelIdLocation);

        if (dateOfVisit == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "date"));
        }

        if (dateOfVisit.trim().equals("")) {
            throw new IllegalValueException("Please enter the correct date format");
        }

        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate modelDate = LocalDate.parse(dateOfVisit, inputFormat);
        return new Visit(modelPerson, modelLocation, modelDate);
    }
}
