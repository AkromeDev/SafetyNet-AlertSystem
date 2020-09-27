package com.safetynet.alertsystem.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import com.safetynet.alertsystem.model.FireStations;
import com.safetynet.alertsystem.model.MedicalRecords;
import com.safetynet.alertsystem.repository.FireStationRepository;

public class SummaryUtil {
	
	FireStationRepository fireRepo = new FireStationRepository();
	
	public Integer findNumberOfChildren(ArrayList<MedicalRecords> medicalRecords) {
		
		Integer numberOfChildren = 0;
		
		for (MedicalRecords record: medicalRecords) {
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/d/yyyy");
			String date = record.getBirthdate();

			//convert String to LocalDate
			LocalDate localBirthDate = LocalDate.parse(date, formatter);
			LocalDate today = LocalDate.now();
			
			long years = ChronoUnit.YEARS.between(localBirthDate, today);
			
			if (years < 18) {
				numberOfChildren++;
			}
		}

		return numberOfChildren;
	}

	public Integer findNumberOfAdults(ArrayList<MedicalRecords> medicalRecords) {
		
		Integer numberOfAdults = 0;
		
		for (MedicalRecords record: medicalRecords) {
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/d/yyyy");
			String date = record.getBirthdate();

			//convert String to LocalDate
			LocalDate localBirthDate = LocalDate.parse(date, formatter);
			
			
			LocalDate today = LocalDate.now();
			long years = ChronoUnit.YEARS.between(localBirthDate, today);
			
			if (years >= 18) {
				numberOfAdults++;
			}
		}

		return numberOfAdults;
	}

	public String findStationFromAddress(String address) {
		
		ArrayList<FireStations> fireStations = fireRepo.getFirestations();
		FireStations searchedStation = new FireStations();
		
		for (FireStations station: fireStations) {
				if (station.getAddress().equals(address)) {
					searchedStation = station;
			}
		}
		return searchedStation.getStation().toString();
	}

}
