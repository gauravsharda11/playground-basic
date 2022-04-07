package com.smile.playground.smile.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smile.playground.smile.service.PatientService;

@RestController
@RequestMapping("/api")
public class PatientRestController {

	@Autowired
	private PatientService patientService;

	private List<String> thePatients;

	@GetMapping("/readPatients")
	public List<String> getPatients() {
		return patientService.getPatients();
	}

	@GetMapping("/readPatients/{family}")
	public List<String> getCustomer(@PathVariable String family) {

		thePatients = patientService.getPatient(family);

		if (thePatients == null) {
			throw new RuntimeException("Patient name not found - " + family);
		}
		return thePatients;
	}

}