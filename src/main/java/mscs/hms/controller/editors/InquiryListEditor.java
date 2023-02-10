package mscs.hms.controller.editors;

import java.beans.PropertyEditorSupport;

import org.springframework.util.StringUtils;
import java.util.List;
import java.util.ArrayList;
import jakarta.annotation.Nullable;
import mscs.hms.model.Inquiry;
import mscs.hms.service.InquiryService;

public class InquiryListEditor extends PropertyEditorSupport{
    InquiryService inquiryService;

	boolean allowEmpty;
    public InquiryListEditor(InquiryService inquiryService, boolean allowEmpty) {
        this.inquiryService = inquiryService;
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
                List<Inquiry> list = new ArrayList<>();
                for(String idString : StringUtils.tokenizeToStringArray(text, ",")){
                    list.add(inquiryService.getById(Integer.parseInt(idString)));
                }
				setValue(list);
			}
			catch (Exception ex) {
				throw new IllegalArgumentException("Could not get Inquiry %s: " + ex.getMessage(), ex);
			}
		}
	}	
}
