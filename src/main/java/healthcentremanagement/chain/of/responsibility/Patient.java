package healthcentremanagement.chain.of.responsibility;

import java.time.LocalDate;

public class Patient {
    private final String identification;
    private final LocalDate currentdate;

    public Patient(String identification, LocalDate currentdate) {
        this.identification = identification;
        this.currentdate = currentdate;
    }

    public String getIdentification() {
        return identification;
    }

    public LocalDate getCurrentdate() {
        return currentdate;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "identification='" + identification + '\'' +
                ", currentdate=" + currentdate +
                '}';
    }
}
