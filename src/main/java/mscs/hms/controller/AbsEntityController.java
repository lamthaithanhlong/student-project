package mscs.hms.controller;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.servlet.ModelAndView;

public abstract class AbsEntityController<T> extends AbsBaseController {
    /**
     * 
     * @return type of the entity e.g. Company.class
     */
    public abstract Class<?> getClassType();

    /***
     * 
     * @return the theymeleaf path of edit page. E.g. /company_edit
     */
    public abstract String getEditViewPath();
    /***
     * 
     * @return the theymeleaf path of list page. E.g. /companies
     */
    public abstract String getListViewPath();
    /***
     * 
     * @return the theymeleaf path of new page. E.g. /company_new
     */
    public abstract String getNewViewPath();
    /***
     * 
     * @return the controller path of crud actions. E.g. /company
     */
    public abstract String getCrudPath();

    protected List<ViewField> getPrivateFields(Class<?> classType) {
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
        if (classType.getSuperclass() != null) {
            list.addAll(getPrivateFields(classType.getSuperclass()));
        }
        return list;
    }

    protected void addViewGenerationProperties(ModelAndView modelAndView) {
        modelAndView.addObject("fields", getPrivateFields(getClassType()));
        modelAndView.addObject("listViewPath", getListViewPath());
        modelAndView.addObject("newViewPath", getNewViewPath());
        modelAndView.addObject("editViewPath", getEditViewPath());
        modelAndView.addObject("crudPath", getCrudPath());
    }
    
    protected ModelAndView getListEntitiesModelView(Iterable<? extends Object> objects) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("objects", objects);
        modelAndView.setViewName("company_list");
        addViewGenerationProperties(modelAndView);
        return modelAndView;
    }

    protected ModelAndView getEditViewModel(Object object, String action) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("object", object);
        modelAndView.addObject("action", action);
        modelAndView.setViewName(getEditViewPath());
        addViewGenerationProperties(modelAndView);
        return modelAndView;
    }
}