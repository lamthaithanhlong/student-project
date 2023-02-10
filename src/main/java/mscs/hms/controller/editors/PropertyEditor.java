package mscs.hms.controller.editors;

import java.beans.PropertyEditorSupport;

import org.springframework.util.StringUtils;
import jakarta.annotation.Nullable;
import mscs.hms.service.PropertyService;

public class PropertyEditor extends PropertyEditorSupport{
    PropertyService propertyService;

	boolean allowEmpty;
    public PropertyEditor(PropertyService propertyService, boolean allowEmpty) {
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
                setValue(propertyService.getById(Integer.parseInt(text)));
			}
			catch (Exception ex) {
				throw new IllegalArgumentException(String.format("Could not get Property %s due to error %s: ", text, ex));
			}
		}
	}	
}
