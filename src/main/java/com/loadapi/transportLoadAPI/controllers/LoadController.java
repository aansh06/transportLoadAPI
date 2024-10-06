package com.loadapi.transportLoadAPI.controllers;

import com.loadapi.transportLoadAPI.entities.Load;
import com.loadapi.transportLoadAPI.exceptions.InvalidInputException;
import com.loadapi.transportLoadAPI.exceptions.ResourceNotFoundException;
import com.loadapi.transportLoadAPI.repositories.LoadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/load")
public class LoadController {

    @Autowired
    private LoadRepository loadRepository;

    // Create new load
    @PostMapping
    public ResponseEntity<String> addLoad(@RequestBody Load load){
        if (load.getLoadingPoint() == null || load.getUnloadingPoint() == null) {
            throw new InvalidInputException("Loading point and unloading point are required");
        }
        loadRepository.save(load);
        return ResponseEntity.ok("Load details added successfully");

    }

    // Retrieve loads by shipperId
    @GetMapping
    public ResponseEntity<List<Load>> getLoadsByShipperId(@RequestParam String shipperId) {
        if (shipperId == null || shipperId.isEmpty()) {
            throw new InvalidInputException("ShipperId is required");
        }
        List<Load> loads = loadRepository.findByShipperId(shipperId);
        if (loads.isEmpty()) {
            throw new ResourceNotFoundException("No loads found for shipperId: " + shipperId);
        }
        return ResponseEntity.ok(loads);
    }

    // Retrieve load by loadId
    @GetMapping("/{loadId}")
    public ResponseEntity<Load> getLoadById(@PathVariable Long loadId) {
        Load load = loadRepository.findById(loadId)
                .orElseThrow(() -> new ResourceNotFoundException("Load not found with id: " + loadId));
        return ResponseEntity.ok(load);
    }

    // Update load details
    @PutMapping("/{loadId}")
    public ResponseEntity<Load> updateLoad(@PathVariable Long loadId, @RequestBody Load loadDetails) {
        Load load = loadRepository.findById(loadId)
                .orElseThrow(() -> new ResourceNotFoundException("Load not found with id: " + loadId));

        load.setLoadingPoint(loadDetails.getLoadingPoint());
        load.setUnloadingPoint(loadDetails.getUnloadingPoint());
        load.setProductType(loadDetails.getProductType());
        load.setTruckType(loadDetails.getTruckType());
        load.setNoOfTrucks(loadDetails.getNoOfTrucks());
        load.setWeight(loadDetails.getWeight());
        load.setComment(loadDetails.getComment());
        load.setDate(loadDetails.getDate());

        Load updatedLoad = loadRepository.save(load);
        return ResponseEntity.ok(updatedLoad);
    }

    // Delete load by loadId
    @DeleteMapping("/{loadId}")
    public ResponseEntity<Void> deleteLoad(@PathVariable Long loadId) {
        if (!loadRepository.existsById(loadId)) {
            throw new ResourceNotFoundException("Load not found with id: " + loadId);
        }
        loadRepository.deleteById(loadId);
        return ResponseEntity.noContent().build();

    }


}
