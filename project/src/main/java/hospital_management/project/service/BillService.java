package hospital_management.project.service;


import hospital_management.project.models.Bill;
import hospital_management.project.repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BillService {
    @Autowired
    private BillRepository billRepository;
    public Page<Bill> getAllBills(int page, int size){
        try {
            System.out.println("Fetching all Bills from service");
            Pageable pageable = PageRequest.of(page,size);
            return billRepository.findAll(pageable);

        } catch (Exception e){
            System.out.println("Error message: " + e.getMessage());
            return null;
        }
    }
    public Bill getBillById(Long id){
        try {
            System.out.println("Getting bill details of id: " + id);
            Optional<Bill> billRes = billRepository.findById(id);
            return billRes.orElse(null);
        }
        catch (Exception e){
            System.out.println("Error message: " + e.getMessage());
            return  null;
        }
    }
    public Bill generateBill(Long patientId, double amount){
        Bill bill = new Bill();
        bill.setPatientId(patientId);
        bill.setAmount(amount);
        bill.setStatus("PAID");
        return billRepository.save(bill);
    }
    public Bill createBill(Bill bill){
        try {
            System.out.println("Bill generated successfully");
            return billRepository.save(bill);

        } catch (Exception e) {
            System.out.println("Error message: " + e.getMessage());
            return null;
        }
    }
    public void deleteBill(Long id){
        try {
            System.out.println("Bill with id: " + id +"deleted");
            billRepository.deleteById(id);
        } catch (Exception e) {
            System.out.println("Error message: " + e.getMessage());
        }
    }
    public Bill updateBill(Long id, Bill updatedBill){
        try {
            Optional<Bill> billRes = billRepository.findById(id);
            if(billRes.isPresent()){
                Bill b = billRes.get();
                b.setAmount(updatedBill.getAmount());
                b.setPatientId(updatedBill.getPatientId());
                b.setStatus(updatedBill.getStatus());
                billRepository.save(b);
                System.out.println("Bill details with id: " + id + "updated successfully");
                return updatedBill;
            } else {
                System.out.println("Bill details with id: " + id + "not found");
                return null;
            }
        } catch (Exception e) {
            System.out.println("Error message: " + e.getMessage());
            return null;
        }
    }
}
