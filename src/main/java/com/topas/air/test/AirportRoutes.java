package com.topas.air.test;

import com.amadeus.Amadeus;
import com.amadeus.Params;
import com.amadeus.exceptions.ResponseException;
import com.amadeus.referencedata.Locations;
import com.amadeus.resources.Destination;
import com.amadeus.resources.Location;

import lombok.extern.slf4j.Slf4j;

// How to install the library at https://github.com/amadeus4dev/amadeus-java
@Slf4j
public class AirportRoutes {

	public static void main(String[] args) throws ResponseException {

		Amadeus amadeus = Amadeus.builder("SHrMbcnhhjK6ekJgDLPT9FwbeKa2PSqK", "9KpkICtEnUgcGAUp").build();

		Destination[] directDestinations = amadeus.airport.directDestinations
				.get(Params.with("departureAirportCode", "MAD"));

		for (Destination destination : directDestinations) {

			log.info("destination getResponse={}", destination.getResponse().getResult().toString());

		}

		if (directDestinations[0].getResponse().getStatusCode() != 200) {
			System.out.println("Wrong status code: " + directDestinations[0].getResponse().getStatusCode());
			System.exit(-1);
		}

//		System.out.println(directDestinations[0]);

//		Amadeus amadeus2 = Amadeus
//		        .builder("REPLACE_BY_YOUR_API_KEY", "REPLACE_BY_YOUR_API_SECRET")
//		        .build();

		Location[] locations = amadeus.referenceData.locations
				.get(Params.with("keyword", "LON").and("subType", Locations.ANY));
		for (Location location : locations) {
			log.info("location={}", location.toString());
			log.info("location getResponse={}", location.getResponse().getResult().toString());

		}

//		System.out.println(locations);

	}
}
