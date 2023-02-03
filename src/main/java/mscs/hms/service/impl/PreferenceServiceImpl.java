package mscs.hms.service.impl;

import mscs.hms.model.Preference;
import mscs.hms.repository.PreferenceRepository;
import mscs.hms.service.PreferenceService;
import org.springframework.beans.factory.annotation.Autowired;

public class PreferenceServiceImpl implements PreferenceService {

    @Autowired
    private PreferenceRepository preferenceRepository;

    @Override
    public Preference save(Preference preference) {
        return preferenceRepository.save(preference);
    }

    @Override
    public Preference getById(Integer id) {
        return preferenceRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(Integer id) {
        preferenceRepository.deleteById(id);
    }

    @Override
    public Iterable<Preference> findAll() {
        return preferenceRepository.findAll();
    }
}
