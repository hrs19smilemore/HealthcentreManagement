package healthcentremanagement.chain.of.responsibility;

public class SectionChain {
    public SectionHandler firstSectionHandler;
    public SectionChain() {
        AppointmentSectionHandler appointmentSectionHandler = new AppointmentSectionHandler();
        FamilyMedicineSectionHandler familyMedicineSectionHandler = new FamilyMedicineSectionHandler();
        ChildhealthSectionHandler childhealthSectionHandler = new ChildhealthSectionHandler();
        PharmacySectionHandler pharmacySectionHandler = new PharmacySectionHandler();
        appointmentSectionHandler.setNextSectionHandler(familyMedicineSectionHandler);
        familyMedicineSectionHandler.setNextSectionHandler(childhealthSectionHandler);
        childhealthSectionHandler.setNextSectionHandler(pharmacySectionHandler);
        this.firstSectionHandler = appointmentSectionHandler;
    }
}
