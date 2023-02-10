package mscs.hms.helper;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import mscs.hms.dto.paging.Direction;
import mscs.hms.model.Property;

public final class PropertyComparators {

    static class Key {
        String name;
        Direction dir;
        public Key(String name, Direction dir) {
            this.name = name;
            this.dir = dir;
        }
        public String getName() {
            return name;
        }
        public Direction getDir() {
            return dir;
        }
        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((name == null) ? 0 : name.hashCode());
            result = prime * result + ((dir == null) ? 0 : dir.hashCode());
            return result;
        }
        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Key other = (Key) obj;
            if (name == null) {
                if (other.name != null)
                    return false;
            } else if (!name.equals(other.name))
                return false;
            if (dir != other.dir)
                return false;
            return true;
        }
    }

    static Map<Key, Comparator<Property>> map = new HashMap<>();

    static {
        map.put(new Key("noOfBathRooms", Direction.asc), Comparator.comparing(Property::getNoOfBathRooms));
        map.put(new Key("noOfBathRooms", Direction.desc), Comparator.comparing(Property::getNoOfBathRooms)
                                                           .reversed());

        map.put(new Key("noOfRooms", Direction.asc), Comparator.comparing(Property::getNoOfRooms));
        map.put(new Key("noOfRooms", Direction.desc), Comparator.comparing(Property::getNoOfRooms)
                                                                 .reversed());
        
    }

    public static Comparator<Property> getComparator(String name, Direction dir) {
        return map.get(new Key(name, dir));
    }

    private PropertyComparators() {
    }
}