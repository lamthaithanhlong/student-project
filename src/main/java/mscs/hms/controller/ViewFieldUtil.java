package mscs.hms.controller;
import org.springframework.context.annotation.Configuration;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

@Configuration
public class ViewFieldUtil {
   public Object getFieldValue(Object ob, String fieldName) throws Exception{
      PropertyDescriptor pd = new PropertyDescriptor(fieldName, ob.getClass());
      Method getter = pd.getReadMethod();
      return getter.invoke(ob);
   }

   public String getDisplayName(String fieldName) throws Exception{
      String displayName = fieldName.replaceAll("\\d+", "").replaceAll("(.)([A-Z])", "$1 $2");
      displayName = fieldName.substring(0, 1).toUpperCase() + displayName.substring(1);      
      return displayName;
   }

   public String getDeleteCrudPath(String crudPathMain, String idFieldName, Object ob) throws Exception{
      return crudPathMain + "delete?" + idFieldName + "=" + getFieldValue(ob, idFieldName);
   }
}
