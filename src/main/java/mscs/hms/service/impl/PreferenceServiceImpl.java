package mscs.hms.service.impl;

import mscs.hms.model.Preference;
import mscs.hms.repository.PreferenceRepository;
import mscs.hms.service.PreferenceService;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
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
    public List<Preference> findAll() {
        return preferenceRepository.findAll();
    }

    public Page<Preference> getAll(String searchString, Integer page, Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(page, pageSize);
        if (searchString == null || searchString.isBlank()) {
            return preferenceRepository.findAll(pageRequest);
        } else if (NumberUtils.isParsable(searchString)) {
            return preferenceRepository.findByNoOfRoomsOrNoOfBathRooms(Integer.parseInt(searchString), Integer.parseInt(searchString), pageRequest);
        } else {
            return preferenceRepository.findByTitleContainsIgnoreCase(searchString, pageRequest);
        }
    }
}
