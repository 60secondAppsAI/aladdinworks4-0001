package com.aladdinworks4.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;
import java.util.List;
import java.sql.Timestamp;
import java.time.Year;
import jakarta.persistence.Transient;



@Entity
@Table(name="generator_units")
@Getter @Setter @NoArgsConstructor
public class GeneratorUnit {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
  	@Column(name="generator_unit_id")
	private Integer generatorUnitId;
    
  	@Column(name="power_output")
	private int powerOutput;
    
  	@Column(name="fuel_type")
	private String fuelType;
    
	




}
