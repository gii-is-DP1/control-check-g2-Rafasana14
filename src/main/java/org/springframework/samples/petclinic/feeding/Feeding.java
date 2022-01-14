package org.springframework.samples.petclinic.feeding;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.pet.Pet;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="feedings")
public class Feeding extends BaseEntity{
    //Integer id;
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	@NotNull
	@Column(name = "start_date")        
    LocalDate startDate;
	
	
	@Min(value = 1)
	@NotNull
	@Column(name="weeks_duration")
    double weeksDuration;
	
	@NotNull
	@ManyToOne(optional=false)
    Pet pet;
	
	@ManyToOne
	@JoinColumn(name = "type_id")
	FeedingType feedingType;
}
