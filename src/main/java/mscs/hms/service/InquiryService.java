package mscs.hms.service;

import java.util.List;

import mscs.hms.model.Inquiry;

public interface InquiryService {
    public Inquiry save(Inquiry inquiry);
    public Inquiry getById(Integer id);
    public void deleteById(Integer id);
    public List<Inquiry> findAll();
}
