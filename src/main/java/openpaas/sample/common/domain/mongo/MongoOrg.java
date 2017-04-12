package openpaas.sample.common.domain.mongo;

import java.util.Date;

import javax.persistence.Id;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Orgs")
public class MongoOrg {
	@Id 
	protected String id;
	 
	protected String label;
	
	protected String desc;
	
	protected String url;
	
	protected Date created;
	
	protected Date modified;
	

	public String getId() {
		return id;
	}


	public void setId(String id) {
		if(id != null && !id.equals(""))
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


	public Date getCreated() {
		return created;
	}


	public void setCreated(Date created) {
		if(created != null)
			this.created = created;
	}


	public Date getModified() {
		return modified;
	}


	public void setModified(Date modified) {
		if(modified != null)
			this.modified = modified;
	}
}
