package fpoly.edu.jewelleryStore.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import fpoly.edu.jewelleryStore.Entity.Status;
import fpoly.edu.jewelleryStore.Repository.StatusRepository;

@Service
public class StatusServiceImpl implements StatusService {

    @Autowired
    private StatusRepository statusRepository;

    @Override
    public List<Status> findAll() {
        return statusRepository.findAll();
    }

    @Override
    public Status findById(Integer id) {
        Optional<Status> optionalStatus = statusRepository.findById(id);
        return optionalStatus.orElse(null);
    }

    @Override
    public Status save(Status Status) {
        return statusRepository.save(Status);
    }

    @Override
    public void deleteById(Integer id) {
        statusRepository.deleteById(id);
    }
    @Override
    public Page<Status> findPaginated(Pageable pageable) {
        return statusRepository.findAll(pageable);
    }
}
