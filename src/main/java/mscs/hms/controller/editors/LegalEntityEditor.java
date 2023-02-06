package mscs.hms.controller.editors;

import java.beans.PropertyEditorSupport;

import org.springframework.util.StringUtils;
import java.util.List;
import java.util.ArrayList;
import jakarta.annotation.Nullable;
import mscs.hms.model.LegalEntity;
import mscs.hms.service.LegalEntityService;
import mscs.hms.service.impl.UserServiceImpl;

public class LegalEntityEditor extends PropertyEditorSupport{
    LegalEntityService legalEntityService;

	boolean allowEmpty;
    public LegalEntityEditor(LegalEntityService legalEntityService, boolean allowEmpty) {
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
                for(String roleIdString : StringUtils.tokenizeToStringArray(text, ",")){
                    list.add(legalEntityService.get(Integer.parseInt(roleIdString)));
                }
				setValue(list);
			}
			catch (Exception ex) {
				throw new IllegalArgumentException("Could not get Role %s: " + ex.getMessage(), ex);
			}
		}
	}	
}
