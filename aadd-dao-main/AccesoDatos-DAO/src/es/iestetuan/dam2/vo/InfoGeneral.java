package es.iestetuan.dam2.vo;

import java.util.ArrayList;
import java.util.List;

public class InfoGeneral {
	private String estilo;
	private List<String> info = new ArrayList<String>();

	public String getEstilo() {
		return this.estilo;
	}

	public void setEstilo(String estilo) {
		this.estilo = estilo;
	}

	public List<String> getInfo() {
		return info;
	}

	public void addInfo(String info) {
		this.info.add(info);
	}
}
