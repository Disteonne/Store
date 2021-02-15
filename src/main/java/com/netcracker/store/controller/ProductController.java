package com.netcracker.store.controller;

import com.netcracker.store.check.CheckForPatchMapping;
import com.netcracker.store.dto.ProductDto;
import com.netcracker.store.dto.UserDto;
import com.netcracker.store.entity.Address;
import com.netcracker.store.entity.Product;
import com.netcracker.store.exeption.NotFoundException;
import com.netcracker.store.exeption.ResponseInputException;
import com.netcracker.store.service.ProductDtoService;
import com.netcracker.store.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/product") //,headers = "application/json;charset=UTF-8"
public class ProductController {

    @Autowired
    private  ProductService service;

    @Autowired
    private ProductDtoService productDtoService;

    private final CheckForPatchMapping checkForPatchMapping=new CheckForPatchMapping();

    @GetMapping("/getAll")
    public List<Product> getAll(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable(value = "id") int id){
        return service.getProductById(id);
    }

    @PostMapping("/save")
    public Map<String,Boolean> save(@RequestBody Product product) throws NotFoundException {
        return service.saveProduct(product);
    }

    @PostMapping("/saveDto")
    public String saveDto(@RequestBody ProductDto productDto) throws NotFoundException {
        return productDtoService.saveDto(productDto);
    }

    @DeleteMapping("/delete")
    public String delete(@RequestBody ProductDto productDto){
        return productDtoService.deleteDto(productDto);
    }

    @DeleteMapping("/delete/{id}")
    public Map<String,Boolean> delete(@PathVariable(value = "id") int id){
        return service.deleteProductById(id);
    }

    @PutMapping("/update/put")
    public ResponseEntity<String> putUpdate(@RequestBody ProductDto productDto){
        productDtoService.saveDto(productDto);
        return ResponseEntity.ok("Saved");
    }

    @PatchMapping("/update/patch")
    public ResponseEntity<String> patchUpdate(@RequestBody ProductDto productDto) throws IllegalAccessException, NotFoundException, ResponseInputException {
        Map<String,String> fields= checkForPatchMapping.validateObject(productDto);
        for (Map.Entry<String, String> pair : fields.entrySet()
        ) {
            productDtoService.updatePart(pair.getKey(), pair.getValue(), productDto.getId());
        }
        return ResponseEntity.ok("Updated");
    }

    @PatchMapping("/update/name={name}&type={type}&price={price}&count={count}&supplierId={supplierId}&info={info}&id={id}")
    public String fullUpdate(@Valid @PathVariable(value = "name") String name,
                             @Valid @PathVariable(value = "type") String type,
                             @Valid @PathVariable(value = "price") double price,
                             @Valid @PathVariable(value = "count") int count,
                             @Valid @PathVariable(value = "supplierId") int supplierId,
                             @Valid @PathVariable(value = "info") String info,
                             @Valid @PathVariable(value = "id") int id) throws NotFoundException {
       return productDtoService.fullUpdate(name,type,price,count,supplierId,info,id);
    }

    @PatchMapping("/update/whatUpdate={what}&toUpdate={to}&id={id}")
    public String partUpdate(@Valid @PathVariable(value = "what") String whatUpdate,
                             @Valid @PathVariable(value = "to") String toUpdate,
                             @Valid @PathVariable(value = "id") int id) throws NotFoundException, ResponseInputException {
        return productDtoService.updatePart(whatUpdate, toUpdate, id);
    }
}
