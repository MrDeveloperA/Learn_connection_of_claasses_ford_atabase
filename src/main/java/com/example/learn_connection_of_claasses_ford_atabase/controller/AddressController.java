package com.example.learn_connection_of_claasses_ford_atabase.controller;

import com.example.learn_connection_of_claasses_ford_atabase.entity.Address;
import com.example.learn_connection_of_claasses_ford_atabase.entity.Subject;
import com.example.learn_connection_of_claasses_ford_atabase.payload.SubjectDto;
import com.example.learn_connection_of_claasses_ford_atabase.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/address")
public class AddressController {
    @Autowired
    AddressRepository addressRepository;

    //    Create
    @RequestMapping(method = RequestMethod.POST)
    public String addAddress(@RequestBody Address address) {
        Address address1 = new Address();
        address1.setCity(address.getCity());
        address1.setDistrict(address.getDistrict());
        address1.setStreet(address.getStreet());

        addressRepository.save(address1);
        return "Address added";
    }

    //    Read
    @GetMapping
    public List<Address> getAddress() {
        List<Address> addressList = addressRepository.findAll();
        return addressList;
    }

    //  Delete
    @DeleteMapping("/{id}")
    public String deleteAddress(@PathVariable Integer id) {
        try {
            addressRepository.deleteById(id);
            return "Address was deleted";

        } catch (Exception e) {
            return "Error in deleting";
        }
    }

    //    Update
    @PutMapping("/{id}")
    public String editAddress(@PathVariable Integer id, @RequestBody Address address) {
        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (optionalAddress.isPresent()) {
            Address editAddress = optionalAddress.get();
            editAddress.setCity(address.getCity());
            editAddress.setDistrict(address.getDistrict());
            editAddress.setStreet(address.getStreet());

            addressRepository.save(editAddress);
            return "Address edited";
        }
        return "Address was not found";
    }
}
