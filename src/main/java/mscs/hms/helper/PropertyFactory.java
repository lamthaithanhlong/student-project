package mscs.hms.helper;

import mscs.hms.model.Apartment;
import mscs.hms.model.House;
import mscs.hms.model.Property;

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
