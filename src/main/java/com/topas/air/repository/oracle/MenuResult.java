package com.topas.air.repository.oracle;

import java.awt.Menu;
import java.util.List;
import java.util.stream.Collectors;

import lombok.Data;

@Data
public class MenuResult {
	private Long id;

	private String name;

//	    private int listOrder;

	private List<Files> children;

	public MenuResult(final Dir menu) {
		this.id = menu.getId();
		this.name = menu.getName();
//		this.listOrder = menu.getListOrder();
//		this.children = menu.getChildren().stream().map(MenuResult::new).collect(Collectors.toList());
	}
}
