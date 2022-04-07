package com.basic.task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Patient;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import ca.uhn.fhir.rest.client.interceptor.LoggingInterceptor;

public class SampleClient {

	public static void main(String[] theArgs) {// Create a FHIR client
		FhirContext fhirContext = FhirContext.forR4();
		IGenericClient client = fhirContext.newRestfulGenericClient("http://hapi.fhir.org/baseR4");
		client.registerInterceptor(new LoggingInterceptor(false));

		// Search for Patient resources
		Bundle response = client.search().forResource("Patient").where(Patient.FAMILY.matches().value("SMITH"))
				.returnBundle(Bundle.class).execute();
		
		/*
		 * Basic Tasks
		 * 
		 * calling printing method to print all patients
		 */	
		SampleClient.printingPatient(response);
	}

	//method for printing resources
	public static void printingPatient(Bundle response) {
		List<Patient> patients = new ArrayList<Patient>();

		response.getEntry().stream().forEach((e) -> {
			patients.add((Patient) e.getResource());
		});

		//sorting patients
		SampleClient.sortPatients(patients);

		for (Patient patient : patients) {
			System.out.println(((Patient) patient).getNameFirstRep().getGivenAsSingleString() + " "
					+ ((Patient) patient).getNameFirstRep().getFamily() + " (" + ((Patient) patient).getBirthDate()
					+ ")");
		}

	}

	//method for sorting patients
	public static void sortPatients(List<Patient> patients) {
		Collections.sort(patients, new Comparator<Patient>() {
			public int compare(Patient p1, Patient p2) {
				int res = p1.getNameFirstRep().getGivenAsSingleString()
						.compareToIgnoreCase(p2.getNameFirstRep().getGivenAsSingleString());
				if (res != 0)
					return res;
				return p1.getNameFirstRep().getGivenAsSingleString()
						.compareToIgnoreCase(p2.getNameFirstRep().getGivenAsSingleString());
			}
		});
	}

}