package com.codeclan.example.WhiskyTracker;

import com.codeclan.example.WhiskyTracker.models.Distillery;
import com.codeclan.example.WhiskyTracker.models.Whisky;
import com.codeclan.example.WhiskyTracker.repositories.DistilleryRepository;
import com.codeclan.example.WhiskyTracker.repositories.WhiskyRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@ActiveProfiles("test") //Indicates it's a test profile so will not run DataLoader
@SpringBootTest
public class WhiskyTrackerApplicationTests {

	@Autowired
	DistilleryRepository distilleryRepository;

	@Autowired
	WhiskyRepository whiskyRepository;

	@Test
	public void contextLoads() {
	}

	@Test
	public void canGetDistilleriesAged12() {
		Distillery macallan = new Distillery("Macallan", "Speyside");
		distilleryRepository.save(macallan);
		Whisky whisky = new Whisky("The Macallan Anniversary Malt", 1995, 12, macallan);
		whiskyRepository.save(whisky);
		List<Distillery> found = distilleryRepository.findByWhiskiesAge(12);
		// assertEquals(1, found.size());
		// assertEquals("Macallan", found.get(0).getName());
		assertEquals(12, found.get(0).getWhiskies().get(0).getAge());
	}

}
