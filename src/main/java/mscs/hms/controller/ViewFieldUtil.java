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

   public String initCaps(String fieldName) throws Exception{
      return fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
   }

   public String getDeleteCrudPath(String crudPathMain, String idFieldName, Object ob) throws Exception{
      return crudPathMain + "delete?" + idFieldName + "=" + getFieldValue(ob, idFieldName);
   }
}
