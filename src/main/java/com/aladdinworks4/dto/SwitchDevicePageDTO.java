package com.aladdinworks4.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SwitchDevicePageDTO {

	private Integer page = 0;
	private Long totalElements = 0L;

	private List<SwitchDeviceDTO> switchDevices;
}




