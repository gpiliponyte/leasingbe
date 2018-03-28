package itacademy.vehicleleasingbe.leasingbe;

import itacademy.vehicleleasingbe.leasingbe.beans.documents.LeasingForm;
import itacademy.vehicleleasingbe.leasingbe.services.UniqueIdGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Random;

@SpringBootApplication
public class LeasingbeApplication {

	public static void main(String[] args) {
		SpringApplication.run(LeasingbeApplication.class, args);

	}

}
