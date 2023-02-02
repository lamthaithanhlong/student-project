package mscs.hms.controller;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;

public abstract class AbsBaseController {
    protected final Logger LOG = LoggerFactory.getLogger(getClass());
    
    protected static <T> List<ViewField> getPrivateFields(Class<T> classType) {
        List<ViewField> list = new ArrayList<>();
        Field[] fields = classType.getDeclaredFields();
        for (Field field : fields) {
            String modifiers = Modifier.toString(field.getModifiers());
            if(modifiers.contains("private") &&
               !modifiers.contains("static")){
                ViewField viewField = new ViewField();
                viewField.setName(field.getName());
                viewField.setType(field.getType().getSimpleName());
                list.add(viewField);
            }
        }
        return list;
    }

    protected abstract void addViewGenerationProperties(ModelAndView modelAndView);
}
