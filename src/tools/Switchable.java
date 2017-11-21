package tools;

public interface Switchable {
    /**
     * Event triggered when switched from this tool to another
     */
    void doAfterSwitchOut();

    /**
     * Event triggered when switched to this tool from another
     */
    void doOnSwitchIn();


}
