package com.smile.playground.smile.service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

@Service
public class PatientService {
	
	private String dir = "C:\\Users\\Gaurav\\Desktop\\Smile\\smile\\src\\main\\resources\\data.txt";
	
	public List<String> getPatients() {
		List<String> thePatients = new ArrayList<String>();
		
		try (Stream<String> stream = Files.lines(Paths.get(dir), StandardCharsets.UTF_8)) {
			stream.forEach(s -> thePatients.add(s));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return thePatients;
	}

	
	@SuppressWarnings("null")
	public List<String> getPatient(String family) {
		List<String> thePatients = new ArrayList<String>();
		
		try (Stream<String> stream = Files.lines(Paths.get(dir), StandardCharsets.UTF_8)) {
			stream.forEach(s -> thePatients.add(s));
		} catch (IOException e) {
			e.printStackTrace();
		}

		List<String> withFamilyName = new ArrayList<String>();
		for (int i = 0; i < thePatients.size(); i++) {
			if(thePatients.get(i).contains(family))
				withFamilyName.add(thePatients.get(i));
		}
		return withFamilyName;
	}

}
