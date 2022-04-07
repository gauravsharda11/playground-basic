package com.intermediate.task;

import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Patient;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import ca.uhn.fhir.rest.client.api.ServerValidationModeEnum;
import ca.uhn.fhir.rest.client.interceptor.LoggingInterceptor;

public class SampleClient {

	public static void main(String[] theArgs) {

		// Create a FHIR client
		FhirContext fhirContext = FhirContext.forR4();
		fhirContext.getRestfulClientFactory().setServerValidationMode(ServerValidationModeEnum.NEVER);
		String serverBase = "http://localhost:1122/api";
		IGenericClient client = fhirContext.newRestfulGenericClient(serverBase);
		client.registerInterceptor(new LoggingInterceptor(false));
		
		

		// Search for Patient resources
		Bundle response = client.search().forResource("Patient").where(Patient.FAMILY.matches().value("smith"))
				.returnBundle(Bundle.class).execute();

	}

}