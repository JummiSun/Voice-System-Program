import java.util.Objects;

class Candidate {
    public String firstName;
    public String lastName;
    public int partiNumber;

    public Candidate(String firstName, String lastName, int partiNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.partiNumber = partiNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getPartiNumber() {
        return partiNumber;
    }


    public String getFullname() {
        return firstName + " " + lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Candidate candidate = (Candidate) o;
        return partiNumber == candidate.partiNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(partiNumber);
    }
}
