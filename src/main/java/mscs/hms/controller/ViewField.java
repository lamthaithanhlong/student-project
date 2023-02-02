package mscs.hms.controller;

public class ViewField {
    private String name;
    private String type;
    private String viewType;
    private String placeHolder;
    private String pattern;
    private String validationMessage;
    private boolean required;
    private boolean idColumn;
    private boolean generatedColumn;
    private boolean associationField;
    public ViewField() {
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    } 
    public String getViewType() {
        return viewType;
    }
    public void setViewType(String viewType) {
        this.viewType = viewType;
    }
    public String getPlaceHolder() {
        return placeHolder;
    }
    public void setPlaceHolder(String placeHolder) {
        this.placeHolder = placeHolder;
    }
    public String getPattern() {
        return pattern;
    }
    public void setPattern(String pattern) {
        this.pattern = pattern;
    }
    public String getValidationMessage() {
        return validationMessage;
    }
    public void setValidationMessage(String validationMessage) {
        this.validationMessage = validationMessage;
    }
    public boolean isRequired() {
        return required;
    }
    public void setRequired(boolean required) {
        this.required = required;
    }
    public boolean isIdColumn() {
        return idColumn;
    }
    public void setIdColumn(boolean idColumn) {
        this.idColumn = idColumn;
    }
    public boolean isGeneratedColumn() {
        return generatedColumn;
    }
    public void setGeneratedColumn(boolean generatedColumn) {
        this.generatedColumn = generatedColumn;
    }
    public boolean isAssociationField() {
        return associationField;
    }
    public void setAssociationField(boolean associationField) {
        this.associationField = associationField;
    }  
}
