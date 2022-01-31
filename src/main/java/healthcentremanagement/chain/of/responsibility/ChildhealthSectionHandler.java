package healthcentremanagement.chain.of.responsibility;

import healthcentremanagement.services.AppointmentService;

public class ChildhealthSectionHandler implements SectionHandler{

    private SectionHandler nextSectionHandler;
    SectionDecider sectionDecider = new SectionDecider();
    AppointmentService appointmentService = new AppointmentService();

    @Override
    public void setNextSectionHandler(SectionHandler sectionHandler) {
        this.nextSectionHandler = sectionHandler;
    }

    @Override
    public void check(Patient patient) {
        if (patient == null || patient.getIdentification() == null || patient.getCurrentdate() == null) {
            System.out.println("No valid patient given");
        } else if (sectionDecider.beAtChildhealthSection(patient)){
            System.out.println("Send patient to Childhealth section at " +
                    appointmentService.returnTimeForChildhealthSection(patient));
        } else {
            this.nextSectionHandler.check(patient);
        }

    }
}
