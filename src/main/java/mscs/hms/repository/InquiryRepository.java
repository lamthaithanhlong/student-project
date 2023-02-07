package mscs.hms.repository;

import mscs.hms.model.Inquiry;

import org.springframework.data.jpa.repository.JpaRepository;

public interface InquiryRepository extends JpaRepository<Inquiry, Integer> {
}
