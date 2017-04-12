package openpaas.sample.web.controller.support;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class OrgDTO {
	protected String id;
	protected String label;
	protected String desc;
	protected String url;
	protected String created;
	protected String modified;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}
	public String getModified() {
		return modified;
	}
	public void setModified(String modified) {
		this.modified = modified;
	}

	public static class Response extends OrgDTO{
		private static final DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		public Response(){
			desc = "";
			url = "";
			modified = "";
		}
		
		@Override
		public void setDesc(String desc) {
			if(desc != null)
				this.desc = desc;
		}
		@Override
		public void setUrl(String url) {
			if(url != null)
				this.url = url;
		}
		@Override
		public void setCreated(String created) {
			if(created != null)
				this.created = created;
		}
		@Override
		public void setModified(String modified) {
			if(modified != null)
				this.modified = modified;
		}
		public void setCreated(Date created) {
			this.created = format.format(created);
		}
		public void setModified(Date modified) {
			if(modified != null)
				this.modified = format.format(modified);
		}
	}

}
