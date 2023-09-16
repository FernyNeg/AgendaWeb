package itsh.isic.models;

import java.util.List;

public class ConsultaList<T> extends BaseModel {
	private List<T> list;
	private String param;

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

}
