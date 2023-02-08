package mscs.hms.controller.util;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
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
    private boolean manyAssociation;    
}
