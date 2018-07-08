package web.components;

import java.util.regex.Pattern;

public class Query extends Component {

    public Query(String property){
        super(property);
    }

    public static Pattern regex(){
        return Pattern.compile("(?<query>(\\?[\\p{Alnum}-._~%!$&'()*+,;=:@/?]+)?)");
    }

    protected void validate(String property) {

        exists(property);

        if (!property.matches(regex().pattern())){
            throw new IllegalArgumentException(String.format("Query is invalid: '%s'", property));
        }
    }

    protected void setProperty(String property) {
        this.property = property;
    }
}
