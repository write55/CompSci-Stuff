
public class Guest {

    private String firstName;
    private String lastName;
    private String company;
    private String response;

    // CONSTRUCTOR
    public Guest(String firstName, String lastName, String company, String response) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.company = company;
        this.response = response;
    }

    // GETTERS
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCompany() {
        return company;
    }

    public String getResponse() {
        return response;
    }

    // SETTERS
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    // toString method
    public String toString() {
        String response = getResponse();
        if (response.equals("?")) {
            response = "maybe";
        }
        return "Name: " + getLastName() + ", " + getFirstName() + "\nCompany: " + getCompany() + "\nResponse: "
                + response;
    }

    public int compareGuests(Guest compare) {
        // negative if referenced object is lexically lower than argument
        int firstValue = this.getFirstName().compareTo(compare.getFirstName());
        int lastValue = this.getLastName().compareTo(compare.getLastName());
        if (lastValue != 0) {
            return lastValue;
        } else if (firstValue != 0) {
            return firstValue;
        } else {
            return 0;
        }
    }

}
