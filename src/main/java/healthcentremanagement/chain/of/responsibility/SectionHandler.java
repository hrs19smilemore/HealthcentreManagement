package healthcentremanagement.chain.of.responsibility;


public interface SectionHandler {

    void setNextSectionHandler(SectionHandler nextSectionHandler);

    void check(Patient patient);
}
