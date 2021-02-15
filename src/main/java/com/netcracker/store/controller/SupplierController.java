package com.netcracker.store.controller;

import com.netcracker.store.check.CheckForPatchMapping;
import com.netcracker.store.check.CheckForSortAndPaging;
import com.netcracker.store.dto.SupplierDto;
import com.netcracker.store.exeption.ResponseInputException;
import com.netcracker.store.service.SupplierDtoService;
import com.netcracker.store.entity.Supplier;
import com.netcracker.store.exeption.NotFoundException;
import com.netcracker.store.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/supplier")
public class SupplierController {

    private final SupplierService service;

    @Autowired
    private SupplierDtoService supplierDtoService;


    private final CheckForPatchMapping checkForPatchMapping =new CheckForPatchMapping();

    private final CheckForSortAndPaging checkForSortAndPaging=new CheckForSortAndPaging();

    public SupplierController(SupplierService service) {
        this.service = service;
    }

    @GetMapping("/getAll")
    public List<Supplier> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Supplier> getById(@PathVariable(value = "id") int id) throws NotFoundException {
        return service.getSupplierById(id);
    }

    //с добавлением адреса (вложено)
    @PostMapping("/save")
    public Map<String, Boolean> save(@RequestBody Supplier supplier) throws NotFoundException {
        return service.saveSupplier(supplier);
    }

    //с добавлением id адреса
    @PostMapping("/saveDto")
    public String saveDto(@RequestBody SupplierDto supplierDto) {
        return supplierDtoService.saveDto(supplierDto);
    }

    //без вложенного объекта,а просто с id
    @DeleteMapping("/deleteDto")
    public String delete(@RequestBody SupplierDto supplierDto) {
        return supplierDtoService.deleteDto(supplierDto);
    }

    @DeleteMapping("/delete/{id}")
    public Map<String, Boolean> deleteById(@PathVariable(value = "id") int id) throws NotFoundException {
        return service.deleteSupplierById(id);
    }

    @PutMapping("/update/put")
    public ResponseEntity<String> putUpdate(@RequestBody SupplierDto supplierDto){
        supplierDtoService.saveDto(supplierDto);
        return ResponseEntity.ok("Saved");
    }

    @PatchMapping("/update/patch")
    public ResponseEntity<String> patchUpdate(@RequestBody SupplierDto supplierDto) throws IllegalAccessException, NotFoundException, ResponseInputException {
        Map<String,String> fields= checkForPatchMapping.validateObject(supplierDto);
        for (Map.Entry<String, String> pair : fields.entrySet()
        ) {
            supplierDtoService.updatePart(pair.getKey(), pair.getValue(), supplierDto.getId());
        }
        return ResponseEntity.ok("Updated");
    }

    @PatchMapping("/update/name={name}&mail={mail}&addresId={addressId}&id={id}")
    public String fullUpdate(@Valid @PathVariable(value = "name") String name,
                             @Valid @PathVariable(value = "mail") String mail,
                             @Valid @PathVariable(value = "addressId") int addressId,
                             @Valid @PathVariable(value = "id") int id) throws NotFoundException {
        return supplierDtoService.fullUpdate(name, mail, addressId, id);
    }

    @PatchMapping("/update/whatUpdate={what}&toUpdate={to}&id={id}")
    public String partUpdate(@Valid @PathVariable(value = "what") String whatUpdate,
                             @Valid @PathVariable(value = "to") String toUpdate,
                             @Valid @PathVariable(value = "id") int id) throws NotFoundException, ResponseInputException {
        return supplierDtoService.updatePart(whatUpdate, toUpdate, id);
    }


    @GetMapping("/{page}/{size}/{sortBy}/{sortOrder}")
    public List<Supplier> sortAndPaging(@PathVariable(value = "page") int page,
                                     @PathVariable(value = "size") int size,
                                     @PathVariable(value = "sortBy") String sortBy,
                                     @PathVariable(value = "sortOrder") String sortOrder) {
       return service.sortAndPaging(page,size,sortBy,sortOrder);
    }
}
