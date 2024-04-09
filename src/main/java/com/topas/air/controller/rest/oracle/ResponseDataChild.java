package com.topas.air.controller.rest.oracle;

import java.util.List;

import lombok.Data;

@Data
public class ResponseDataChild {
	private String name;
	private List<ResponseDataChild> children;

}
