package openpaas.sample.common.service.common;

import org.javaswift.joss.model.Account;
import org.javaswift.joss.model.Container;
import org.javaswift.joss.model.StoredObject;

public abstract class AbstractGroupService<T> implements GroupService<T>{

	@Override
	public void deleteOldImage(Account account, String oldFullPath, String newFullPath) {
		
		if(oldFullPath == null || oldFullPath.equals("") || oldFullPath.equals(newFullPath) || account == null)
			return;
		
		Container container = account.getContainer("images");
		if(!container.exists())
			return;
		
		StoredObject object = container.getObject(oldFullPath.substring(oldFullPath.lastIndexOf("/")));
		if(object.exists())
			object.delete();
	}
}
