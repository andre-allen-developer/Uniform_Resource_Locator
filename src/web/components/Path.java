package web.components;

import java.util.regex.Pattern;

public class Path extends Component {

    public Path(String property){
        super(property);
    }

    public static Pattern regex(){
        return Pattern.compile("\"(?<path>(/(\\.{1,2}/|[-\\p{Alnum}]+/?)+)*(/?[-\\p{Alnum}]+\\.[-\\p{Alnum}]+)?/?)\"");
    }

    protected void validate(String property) {

        exists(property);

        if (!property.matches(regex().pattern())){
            throw new IllegalArgumentException(String.format("Path is invalid: '%s'", property));
        }
    }

    protected void setProperty(String property) {
        this.property = property;
    }
}
