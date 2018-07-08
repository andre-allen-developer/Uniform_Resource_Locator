package web.components;

public abstract class Component {

    protected String property;

    protected Component(String property){
        validate(property);
        setProperty(property);
    }

    protected abstract void validate(String property);
    protected abstract void setProperty(String property);

    protected void exists(String value){
        if (value == null){
            throw new IllegalArgumentException(getClass().getSimpleName().concat(" is null."));
        }
    }

    public String toString(){
        return (property == null) ? "" : property;
    }

}
