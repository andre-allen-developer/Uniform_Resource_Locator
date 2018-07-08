package web.components;

import java.util.regex.*;

public class Authority extends Component {

    public User user;
    public Host host;
    public Port port;

    public Authority(String property){
        super(property);
    }

    public static Pattern regex(){
        return Pattern.compile(String.format("(?<authority>(%1$s%2$s)?%3$s(%4$s%5$s)?)", User.regex(), "@"
                , Host.regex(), ":"
                , Port.regex()));
    }

    protected void validate(String property){

        exists(property);

        if (!property.matches(regex().pattern())){
            throw new IllegalArgumentException(String.format("Authority is invalid: '%s'", property));
        }
    }

    protected void setProperty(String property){
        if (!(property == null)){
            Matcher matcher = regex().matcher(property);
            if (matcher.matches()){
                if (!(matcher.group("user") == null)){
                    this.user = new User(matcher.group("user"));;
                }
                if (!(matcher.group("host") == null)){
                    this.host = new Host(matcher.group("host"));
                }
                if (!(matcher.group("port") == null)){
                    this.port = new Port(matcher.group("port"));
                }
            }
        }
        this.property = property;
    }

    private void setUser(String property){
        if (!(user == null)){
            this.user = new User(property);
        }
    }

    private void setHost(String property){
        this.host = new Host(property);
    }

    private void setPort(String property){
        if (!(port == null)){
            this.port = new Port(property);
        }
    }

    public static class User extends Component {

        private Username username;
        private Password password;

        public User(String property){
            super(property);
        }

        public static Pattern regex(){
            return Pattern.compile(String.format("(?<user>%1$s((?>%2$s)%3$s)?)", Username.regex(), ":", Password.regex()));
        }

        protected void validate(String property){

            exists(property);

            if (!property.matches(regex().pattern())){
                throw new IllegalArgumentException(String.format("User is invalid: '%s'", property));
            }
        }

        protected void setProperty(String property){
            if (!(property == null)){
                Matcher matcher = regex().matcher(property);
                if (matcher.matches()){
                    if (!(matcher.group("username") == null)){
                        this.username = new Username(matcher.group("username"));;
                    }
                    if (!(matcher.group("password") == null)){
                        this.password = new Password(matcher.group("password"));
                    }
                }
            }
            this.property = property;
        }

        public static class Username extends Component {

            public Username(String property){
                super(property);
            }

            public static Pattern regex(){
                return Pattern.compile("(?<username>([\\w-._~%!$&'()*+,;=]+))");
            }

            protected void validate(String property){

                exists(property);

                if (!property.matches(regex().pattern())){
                    throw new IllegalArgumentException(String.format("Username is invalid: '%s'", property));
                }
            }

            protected void setProperty(String property){
                this.property = property;
            }

        }

        public static class Password extends Component {

            public Password(String property){
                super(property);
            }

            public static Pattern regex(){
                return Pattern.compile("(?<password>([\\w-._~%!$&'()*+,;=]+))");
            }

            protected void validate(String property){

                exists(property);

                if (!property.matches(regex().pattern())){
                    throw new IllegalArgumentException(String.format("Password is invalid: '%s'", property));
                }
            }

            protected void setProperty(String property){
                this.property = property;
            }

        }

    }

    public static class Host extends Component {

        public Host(String property){
            super(property);
        }

        public static Pattern regex(){
            return Pattern.compile("(?<host>\\p{Alpha}+((\\.)([-\\p{Alnum}]+))*)");
        }

        protected void validate(String property){

            exists(property);

            if (!property.matches(regex().pattern())){
                throw new IllegalArgumentException(String.format("Host is invalid: '%s'", property));
            }
        }

        protected void setProperty(String property){
            this.property = property;
        }

    }

    public static class Port extends Component {

        public Port (String property){
            super(property);
        }

        public static Pattern regex(){
            return Pattern.compile("(?<port>(\\p{Digit}+))");
        }

        protected void validate(String property){

            exists(property);

            if (!property.matches(regex().pattern())){
                throw new IllegalArgumentException(String.format("Port is invalid: '%s'", property));
            }

            final int MIN = 1;
            if (Integer.valueOf(property) < MIN){
                throw new IllegalArgumentException(String.format("Port is below minimum: '%s'", property));
            }

            final int MAX = 65535;
            if (Integer.valueOf(property) > MAX){
                throw new IllegalArgumentException(String.format("Port is above maximum: '%s'", property));
            }
        }

        protected void setProperty(String property){
            this.property = property;
        }

    }

}
