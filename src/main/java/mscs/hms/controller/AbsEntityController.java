package mscs.hms.controller;

import java.util.List;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import mscs.hms.controller.util.ViewField;
import mscs.hms.controller.util.ViewFieldUtil;
import org.springframework.data.domain.Page;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.validation.ObjectError;

public abstract class AbsEntityController<T> extends AbsBaseController {
    final Integer DEFAULT_PAGE_NUMBER = 0;
    final Integer DEFAULT_PAGE_SIZE = 3;
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
     * @return the url of list page. E.g. /companies
     */
    public abstract String getListPath();
    /***
     *
     * @return the theymeleaf path of new page. E.g. /company_list
     */
    public abstract String getNewViewPath();
    /***
     * 
     * @return the controller path of crud actions. E.g. /company
     */
    public abstract String getCrudPath();

    protected List<ViewField> getPrivateFields(Class<?> classType) {
        return ViewFieldUtil.getPrivateFields(classType);
    }
    /**
     * 
     * @return the lists for drop down creation. e.g. Company should have a list for Users, since user is an association of Company
     * It is requried to use the same attributeName when registering
     */
    public abstract Dictionary<String, List<?>> getSelectLists();
    

    protected void addViewGenerationProperties(ModelAndView modelAndView) {
        modelAndView.addObject("entityFields", getPrivateFields(getClassType()));
        modelAndView.addObject("listViewPath", getListViewPath());
        modelAndView.addObject("listPath", getListPath());
        modelAndView.addObject("newViewPath", getNewViewPath());
        modelAndView.addObject("editViewPath", getEditViewPath());
        modelAndView.addObject("crudPath", getCrudPath());
        modelAndView.addObject("listObjects", getSelectLists());
    }
    
    protected ModelAndView getListEntitiesModelView(Page<? extends Object> objects) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("objects", objects);
        modelAndView.setViewName(getListViewPath());
        addViewGenerationProperties(modelAndView);
        int totalPages = objects.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            modelAndView.addObject("pageNumbers", pageNumbers);
        }
        return modelAndView;
    }

    protected static String getSearchString(Optional<String> search) {
        String searchString = search.orElse(null);
        return searchString;
    }

    protected int getPageSize(Optional<Integer> size) {
        int pageSize = size.orElse(DEFAULT_PAGE_SIZE);
        pageSize = pageSize > 0 ? pageSize : DEFAULT_PAGE_SIZE;
        return pageSize;
    }

    protected int getCurrentPage(Optional<Integer> page) {
        int currentPage = page.orElse(DEFAULT_PAGE_NUMBER);
        currentPage = currentPage > 0 ? currentPage - 1 : 0;
        return currentPage;
    }

    protected ModelAndView getEditViewModel(Object object, String action) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("object", object);
        modelAndView.addObject("action", action);
        modelAndView.setViewName(getEditViewPath());
        addViewGenerationProperties(modelAndView);
        return modelAndView;
    }

    protected ModelAndView getEditViewModel(Object object, List<ObjectError> errors, String action) {
        ModelAndView modelAndView = getEditViewModel(object, action);
        modelAndView.addObject("errors", errors);
        return modelAndView;
    }    

    protected List<ObjectError> getObjectErrorList(Exception ex) {
        LOG.error("CRUD Error", ex);
        List<ObjectError> errors = new ArrayList<>();
        errors.add(new ObjectError("object", getError(ex)));
        return errors;
    }

    private String getError(Throwable ex){
        if(ex.getCause() == null){
            return ex.getMessage();
        }
        else {
            return getError(ex.getCause());
        }
    }
}