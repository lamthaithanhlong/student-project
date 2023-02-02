package mscs.hms.controller;
import org.springframework.context.annotation.Configuration;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import mscs.hms.entity.constraints.PositiveNumberConstraint;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.annotation.Annotation;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class ViewFieldUtil {
   public static List<ViewField> getPrivateFields(Class<?> classType) {
      List<ViewField> list = new ArrayList<>();
      Field[] fields = classType.getDeclaredFields();
      if (classType.getSuperclass() != null) {
         list.addAll(getPrivateFields(classType.getSuperclass()));
      }
      for (Field field : fields) {
          String modifiers = Modifier.toString(field.getModifiers());
          if(modifiers.contains("private") &&
             !modifiers.contains("static")){
              ViewField viewField = new ViewField();
              viewField.setName(field.getName());
              viewField.setType(field.getType().getSimpleName());
              viewField.setViewType(getViewType(field));
              viewField.setPlaceHolder(getPlaceHolder(field));
              viewField.setValidationMessage(getValidationMessage(field));
              viewField.setRequired(!viewField.getValidationMessage().isBlank());
              viewField.setIdColumn(isIdColumn(field));
              viewField.setGeneratedColumn(isGeneratedColumn(field));
              list.add(viewField);
          }
      }
      return list;
  }

  private static String getViewType(Field field){
     switch(field.getType().getSimpleName()){
         case "Integer":
            return "number";
         case "Long":
            return "number";
         case "Double":
            return "number";
         case "Date":
               return "date";
         default:
            return "text";
     }
  }

  private static String getPlaceHolder(Field field){
     String displayText = "";
      try{
         displayText = getDisplayName(field.getName());            
      }
      catch(Exception ex){
      }
      return displayText;
  }

  private static String getValidationMessage(Field field){
   String placeHolder = "";
   Annotation[] annotations = field.getAnnotations();
   for(Annotation annotation: annotations){
       try{
         if(annotation.annotationType() == NotNull.class)
         {
            placeHolder += field.getAnnotation(NotEmpty.class).message() + " ";            
         }
         if(annotation.annotationType() == NotEmpty.class)
         {
            placeHolder += field.getAnnotation(NotEmpty.class).message() + " ";            
         }
         if(annotation.annotationType() == PositiveNumberConstraint.class)
         {
            placeHolder += field.getAnnotation(PositiveNumberConstraint.class).message() + " ";            
         }          
       }
       catch(Exception ex){ }
   }
   return placeHolder;
}

private static boolean isIdColumn(Field field){
   Annotation[] annotations = field.getAnnotations();
   for(Annotation annotation: annotations){
       if(annotation.annotationType() == Id.class)
         {
            return true;            
         }            
   }
   return false;
}

private static boolean isGeneratedColumn(Field field){
   Annotation[] annotations = field.getAnnotations();
   for(Annotation annotation: annotations){
       if(annotation.annotationType() == GeneratedValue.class)
         {
            return true;            
         }            
   }
   return false;
}

  public Object getFieldValue(Object ob, String fieldName) throws Exception{
      PropertyDescriptor pd = new PropertyDescriptor(fieldName, ob.getClass());
      Method getter = pd.getReadMethod();
      return getter.invoke(ob);
   }

   public static String getDisplayName(String fieldName) throws Exception{
      String displayName = fieldName.replaceAll("\\d+", "").replaceAll("(.)([A-Z])", "$1 $2");
      displayName = fieldName.substring(0, 1).toUpperCase() + displayName.substring(1);      
      return displayName;
   }

   public String getDeleteCrudPath(String crudPathMain, String idFieldName, Object ob) throws Exception{
      return crudPathMain + "delete?" + idFieldName + "=" + getFieldValue(ob, idFieldName);
   }
}
