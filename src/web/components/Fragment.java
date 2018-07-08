package web.components;

import java.util.regex.Pattern;

public class Fragment extends Component {

    public Fragment(String property){
        super(property);
    }

    public static Pattern regex(){
        return Pattern.compile("(?<fragment>(#[\\w-._~%!$&'()*+,;=:@/?]+)?)");
    }

    protected void validate(String property) {

        exists(property);

        if (!property.matches(regex().pattern())){
            throw new IllegalArgumentException(String.format("Fragment is invalid: '%s'", property));
        }
    }

    protected void setProperty(String property) {
        this.property = property;
    }


}
