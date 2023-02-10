package mscs.hms.controller.editors;

import java.beans.PropertyEditorSupport;

import org.springframework.util.StringUtils;
import java.util.List;
import java.util.ArrayList;
import jakarta.annotation.Nullable;
import mscs.hms.model.LegalEntity;
import mscs.hms.service.LegalEntityService;

public class LegalEntityListEditor extends PropertyEditorSupport{
    LegalEntityService legalEntityService;

	boolean allowEmpty;
    public LegalEntityListEditor(LegalEntityService legalEntityService, boolean allowEmpty) {
        this.legalEntityService = legalEntityService;
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
                List<LegalEntity> list = new ArrayList<>();
                for(String idString : StringUtils.tokenizeToStringArray(text, ",")){
                    list.add(legalEntityService.get(Integer.parseInt(idString)));
                }
				setValue(list);
			}
			catch (Exception ex) {
				throw new IllegalArgumentException("Could not get Legal Entity %s: " + ex.getMessage(), ex);
			}
		}
	}	
}
