package mscs.hms.controller.editors;

import java.beans.PropertyEditorSupport;

import org.springframework.util.StringUtils;
import java.util.List;
import java.util.ArrayList;
import jakarta.annotation.Nullable;
import mscs.hms.model.Property;
import mscs.hms.service.PropertyService;

public class PropertyListEditor extends PropertyEditorSupport{
    PropertyService propertyService;

	boolean allowEmpty;
    public PropertyListEditor(PropertyService propertyService, boolean allowEmpty) {
        this.propertyService = propertyService;
        this.allowEmpty = allowEmpty;
	}

    @Override
	public void setAsText(@Nullable String text) throws IllegalArgumentException {
		if (this.allowEmpty && !StringUtils.hasText(text)) {
			// Treat empty String as null value.
			setValue(null);
		}
		else {
			try {
                List<Property> list = new ArrayList<>();
                for(String idString : StringUtils.tokenizeToStringArray(text, ",")){
                    list.add(propertyService.getById(Integer.parseInt(idString)));
                }
				setValue(list);
			}
			catch (Exception ex) {
				throw new IllegalArgumentException("Could not get Property %s: " + ex.getMessage(), ex);
			}
		}
	}	
}
