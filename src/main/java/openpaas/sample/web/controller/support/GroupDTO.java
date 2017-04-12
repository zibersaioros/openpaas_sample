package openpaas.sample.web.controller.support;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.constraints.NotNull;


public class GroupDTO {
	
	protected String id;
	protected String org_id;
	protected String parent_id;
	
	@NotNull
	protected String label;
	protected String desc;
	
	protected String thumb_img_name;
	protected String thumb_img_path;
	
	protected String url;
	
	protected String created;
	protected String modified;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOrg_id() {
		return org_id;
	}
	public void setOrg_id(String org_id) {
		this.org_id = org_id;
	}
	public String getParent_id() {
		return parent_id;
	}
	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
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
	public String getThumb_img_name() {
		return thumb_img_name;
	}
	public void setThumb_img_name(String thumb_img_name) {
		this.thumb_img_name = thumb_img_name;
	}
	public String getThumb_img_path() {
		return thumb_img_path;
	}
	public void setThumb_img_path(String thumb_img_path) {
		this.thumb_img_path = thumb_img_path;
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

	
	public static class Response extends GroupDTO{
		private static final DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		public Response(){
			this.parent_id = "";
			this.desc = "";           
			this.thumb_img_name = ""; 
			this.thumb_img_path = ""; 
			this.url = "";            
			this.modified = "";       
		}
		
		@Override
		public void setParent_id(String parent_id) {
			if(parent_id != null)
				this.parent_id = parent_id;
		}
		
		@Override
		public void setDesc(String desc) {
			if(desc != null)
				this.desc = desc;
		}
		
		@Override
		public void setThumb_img_name(String thumb_img_name) {
			if(thumb_img_name != null)
				this.thumb_img_name = thumb_img_name;
		}
		
		@Override
		public void setThumb_img_path(String thumb_img_path) {
			if(thumb_img_path != null)
				this.thumb_img_path = thumb_img_path;
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
