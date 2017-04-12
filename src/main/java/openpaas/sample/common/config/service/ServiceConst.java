package openpaas.sample.common.config.service;

public interface ServiceConst {
	public interface ServiceId{
		public static final String MYSQL = "openpaas-sample-mysql";
		public static final String CUBRID = "openpaas-sample-cubrid";
		public static final String MONGODB = "openpaas-sample-mongodb";
		public static final String GLUSTERFS = "openpaas-sample-glusterfs";
		public static final String REDIS = "openpaas-sample-redis";
		public static final String RABBITMQ = "openpaas-sample-rabbitmq";
	}
	
	public interface ServiceType {
		public static final String MYSQL = "mysql";
		public static final String CUBRID = "cubrid";
		public static final String MONGODB = "mongo";
		public static final String HSQL = "hsql";
		public static final String GLUSTERFS="glusterfs";
		public static final String REDIS = "redis";
		public static final String RABBITMQ ="rabbitmq";
	}
	
	public interface RabbitConst{
		public static String EXCHANGE_NAME = "rabbit.openpaas.exchange";

		public static String QUEUE_NAME_MYSQL = "rabbit.openpaas.queue.mysql";
		public static String QUEUE_NAME_CUBRID = "rabbit.openpaas.queue.cubrid";
		public static String QUEUE_NAME_MONGO = "rabbit.openpaas.queue.mongo";
		

		public static String ROUTE_NAME_MYSQL = QUEUE_NAME_MYSQL;
		public static String ROUTE_NAME_CUBRID = QUEUE_NAME_CUBRID;
		public static String ROUTE_NAME_MONGO = QUEUE_NAME_MONGO;
	}
	
	public interface State{
		
		public static String NO_CHANGES = "NO_CHANGES";
		public static String ORG_DELETED = "ORG_DELETED";
		public static String ORG_UPDATED = "ORG_UPDATED";
		public static String GROUP_ADDED = "GROUP_ADDED";
		public static String GROUP_DELETED = "GROUP_DELETED";
		public static String GROUP_UPDATED  = "GROUP_UPDATED";
		

	}
}
