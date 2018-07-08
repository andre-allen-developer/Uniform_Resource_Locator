package web.components;

import java.util.regex.Pattern;

public class Scheme extends Component {

    public Scheme(String property){
        super(property);
    }

    public static Pattern regex(){
        return Pattern.compile("(?<scheme>\\p{Alpha}([\\p{Alnum}-+.]*))");
    }

    protected void validate(String property){

        exists(property);

        if (!property.matches(regex().pattern())){
            throw new IllegalArgumentException(String.format("Scheme is invalid: '%s'", property));
        }
    }

    protected void setProperty(String property){
        this.property = property.toLowerCase();
    }

}
