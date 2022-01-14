package org.springframework.samples.petclinic.feeding;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.pet.PetService;
import org.springframework.samples.petclinic.pet.PetType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FeedingController {
	
	private final String VIEWS_FEEDINGS_CREATE_OR_UPDATE_FORM = "feedings/createOrUpdateFeedingForm";
	
	private final PetService petService;
    private final FeedingService feedingService;

    @Autowired
    public FeedingController(PetService petService, FeedingService feedingService) {
    	this.petService = petService;
    	this.feedingService = feedingService;
    }

    @ModelAttribute("pets")
    public Collection<PetType> populatePetTypes() {
    	return this.petService.findPetTypes();
    }
    
    @ModelAttribute("feedingTypes")
    public Collection<FeedingType> populateFeedingTypes() {
    	return this.feedingService.getAllFeedingTypes();
    }
    
    @GetMapping(value = "/feeding/create")
	public String initCreationForm(ModelMap model) {
		Feeding f = new Feeding();
		model.put("feeding", f);
		return VIEWS_FEEDINGS_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping(value = "/feeding/create")
	public String processCreationForm(@Valid Feeding feeding, BindingResult result, ModelMap model) {		
		if (result.hasErrors()) {
			model.put("feeding", feeding);
			return VIEWS_FEEDINGS_CREATE_OR_UPDATE_FORM;
		}
		else {
			try{
				this.feedingService.save(feeding);
            }catch (UnfeasibleFeedingException e) {
                model.put("message", "La mascota seleccionada no se le puede asignar el plan de alimentación especificado.");
                return VIEWS_FEEDINGS_CREATE_OR_UPDATE_FORM;
			}
            return "redirect:/welcome";
		}
	}
}
