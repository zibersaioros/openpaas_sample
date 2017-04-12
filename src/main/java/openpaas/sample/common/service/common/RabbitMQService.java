package openpaas.sample.common.service.common;

public interface RabbitMQService {
	
	public void createQueue(String type, String id);
	
	public void inputQueue(String type, String id, String message);
	
	public String requestQueue(String type, String id);
	

}
