package mscs.hms.entity;

public class PropertyFactory {
    public static Property createProperty(PropertyType type){
        switch(type){
            case Apartment:
                return new Apartment();
            case House:
                return new House();

        }
        return null;
    }
}
