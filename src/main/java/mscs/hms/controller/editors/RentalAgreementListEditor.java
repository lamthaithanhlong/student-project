package mscs.hms.controller.editors;

import java.beans.PropertyEditorSupport;

import org.springframework.util.StringUtils;
import java.util.List;
import java.util.ArrayList;
import jakarta.annotation.Nullable;
import mscs.hms.model.RentalAgreement;
import mscs.hms.service.RentalAgreementService;

public class RentalAgreementListEditor extends PropertyEditorSupport{
    RentalAgreementService rentalagreementService;

	boolean allowEmpty;
    public RentalAgreementListEditor(RentalAgreementService rentalagreementService, boolean allowEmpty) {
        this.rentalagreementService = rentalagreementService;
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
                List<RentalAgreement> list = new ArrayList<>();
                for(String idString : StringUtils.tokenizeToStringArray(text, ",")){
                    list.add(rentalagreementService.getById(Integer.parseInt(idString)));
                }
				setValue(list);
			}
			catch (Exception ex) {
				throw new IllegalArgumentException("Could not get Rental Agreement %s: " + ex.getMessage(), ex);
			}
		}
	}	
}
