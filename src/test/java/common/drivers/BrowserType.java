package common.drivers;

public enum BrowserType {

    FIREFOX("firefox"), CHROME("chrome");

    private final String name;

    BrowserType(String name) {
        this.name = name;
    }

    public String toString() {
        return this.name;
    }

    /**
     * returns Browser type if found, else throw exception
     *
     * @param name - name of the browser
     * @return BrowserType
     */
    public static BrowserType fromName(String name) {
        for (BrowserType type : BrowserType.values()) {
            if (type.name.equalsIgnoreCase(name)) {
                return type;
            }
        }
        throw new IllegalArgumentException("No Browser type with name " + name + " found");
    }

}
