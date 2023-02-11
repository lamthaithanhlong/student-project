package mscs.hms.repository;

import mscs.hms.model.Inquiry;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

public interface InquiryRepository extends JpaRepository<Inquiry, Integer> {
    @Query("select i from Inquiry i where i.title = :searchString or i.inquiryDetails =:searchString or i.tenant.name =:searchString or i.property.name =:searchString or i.landlord.name =:searchString")
    Page<Inquiry> searchInquiries(String searchString, PageRequest pageRequest);

    Page<Inquiry> findByInquiryDate(LocalDate date, PageRequest pageRequest);
}
