package mscs.hms.controller.editors;

import java.beans.PropertyEditorSupport;

import org.springframework.util.StringUtils;
import java.util.List;
import java.util.ArrayList;
import jakarta.annotation.Nullable;
import mscs.hms.model.RentApplication;
import mscs.hms.service.RentApplicationService;

public class RentApplicationListEditor extends PropertyEditorSupport{
    RentApplicationService rentapplicationService;

	boolean allowEmpty;
    public RentApplicationListEditor(RentApplicationService rentapplicationService, boolean allowEmpty) {
        this.rentapplicationService = rentapplicationService;
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
                List<RentApplication> list = new ArrayList<>();
                for(String idString : StringUtils.tokenizeToStringArray(text, ",")){
                    list.add(rentapplicationService.getById(Integer.parseInt(idString)));
                }
				setValue(list);
			}
			catch (Exception ex) {
				throw new IllegalArgumentException("Could not get Rent Application %s: " + ex.getMessage(), ex);
			}
		}
	}	
}
