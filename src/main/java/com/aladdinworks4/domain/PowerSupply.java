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
@Table(name="power_supplys")
@Getter @Setter @NoArgsConstructor
public class PowerSupply {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
  	@Column(name="power_supply_id")
	private Integer powerSupplyId;
    
  	@Column(name="voltage")
	private int voltage;
    
  	@Column(name="current_output")
	private double currentOutput;
    
	




}