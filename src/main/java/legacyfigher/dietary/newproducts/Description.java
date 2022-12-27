package legacyfigher.dietary.newproducts;

import java.util.Objects;

public class Description {
    private String desc;
    private String longDesc;

    private Description(String desc, String longDesc) {
        this.desc = desc;
        this.longDesc = longDesc;

    }

    public static Description of(String desc, String longDesc) {
        return new Description(desc, longDesc);
    }

    public void replace(String charToReplace, String replaceWith) {
        if (isEmpty()) {
            throw new IllegalStateException("null or empty desc");
        }
        longDesc = longDesc.replace(charToReplace, replaceWith);
        desc = desc.replace(charToReplace, replaceWith);
    }

    private boolean isEmpty() {
        return longDesc == null || longDesc.isEmpty() ||

                desc == null || desc.isEmpty();
    }

    public String getFormatted() {
        if(isEmpty()){
            return "";
        }
        return desc + " *** " + longDesc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Description that = (Description) o;
        return Objects.equals(desc, that.desc) && Objects.equals(longDesc, that.longDesc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(desc, longDesc);
    }
}
