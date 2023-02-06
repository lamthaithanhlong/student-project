package mscs.hms.service;

import mscs.hms.model.Preference;
import java.util.List;

public interface PreferenceService {
    public Preference save(Preference preference);
    public Preference getById(Integer id);
    public void deleteById(Integer id);
    public List<Preference> findAll();
}
