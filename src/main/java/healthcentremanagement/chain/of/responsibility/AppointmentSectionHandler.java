package healthcentremanagement.chain.of.responsibility;

public class AppointmentSectionHandler implements SectionHandler {
    SectionDecider sectionType = new SectionDecider();
    private SectionHandler nextSectionHandler;


    @Override
    public void setNextSectionHandler(SectionHandler sectionHandler) {
        this.nextSectionHandler = sectionHandler;
    }

    @Override
    public void check(Patient patient) {
        if (patient == null || patient.getCurrentdate() == null || patient.getIdentification() == null) {
            System.out.println("No valid patient given");
            return;
        }
         if(!sectionType.haveappointment(patient)) {
            System.out.println("You need to make an appointment for the patient");
        } else {
            this.nextSectionHandler.check(patient);
        }
    }
}
