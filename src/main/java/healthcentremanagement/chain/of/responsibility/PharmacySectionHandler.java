package healthcentremanagement.chain.of.responsibility;

public class PharmacySectionHandler implements SectionHandler{

    private SectionHandler sectionHandler;
    SectionDecider sectionDecider = new SectionDecider();

    @Override
    public void setNextSectionHandler(SectionHandler nextSectionHandler) {
        this.sectionHandler = nextSectionHandler;
    }

    @Override
    public void check(Patient patient) {
        if (patient == null || patient.getIdentification() == null || patient.getCurrentdate() == null) {
            System.out.println("No valid patient given");
        } else if (!sectionDecider.beAtPharmacySection(patient)){
            System.out.println("Send patient to Pharmacy");
        } else
            System.out.println("You need a doctor prescription before you can go to the pharmacy\n" + "" +
                    "Ask patient if they want to make an appointment");
    }
}
