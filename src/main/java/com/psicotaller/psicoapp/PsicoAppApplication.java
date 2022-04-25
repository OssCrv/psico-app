package com.psicotaller.psicoapp;

import com.psicotaller.psicoapp.backend.domain.EntitiesProviderServices;
import com.psicotaller.psicoapp.backend.domain.UserAppService;
import com.psicotaller.psicoapp.backend.persistence.entities.Building;
import com.psicotaller.psicoapp.backend.persistence.entities.Facility;
import com.psicotaller.psicoapp.backend.persistence.entities.Reservation;
import com.psicotaller.psicoapp.backend.persistence.entities.UserApp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@SpringBootApplication
public class PsicoAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(PsicoAppApplication.class, args);
	}


	@RestController
	@RequestMapping
	public class HelloWord {


		@Autowired
		private UserAppService userAppService;

		@Autowired
		private EntitiesProviderServices providerServices;

		@RequestMapping("/Hello")
		public List<Facility> helloWord(){

			return providerServices.getAllFacilities();

		}
}

}