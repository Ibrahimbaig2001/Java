package hospital_management.project.controllers;

import hospital_management.project.models.Bill;
import hospital_management.project.models.Patient;
import hospital_management.project.service.BillService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/v1/bills")
public class BillController {
    private final BillService billService;

    public BillController(BillService billService) {
        this.billService = billService;
    }

    @GetMapping
    public Page<Bill> getAllBills(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size){
        System.out.println("fetching the patients details.");
        return billService.getAllBills(page,size);
    }
    @PostMapping
    public Bill createBill(@RequestBody Bill bill){
        System.out.println("Successfully created a bill in db.");
        return billService.createBill(bill);

    }
    @GetMapping("/{id}")
    public Bill getBillById(@PathVariable Long id){
        System.out.println("Fetching patient by id");
        return billService.getBillById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteBill(@PathVariable Long id){
        System.out.println("Deleted Bill of the patient with id: " + id);

    }
    @PutMapping("/{id}")
    public Bill updateBill(@PathVariable Long id, @RequestBody Bill bill){
        System.out.println("Updated the biil of the patient with id: " + id);
        return billService.updateBill(id, bill);

    }
}
