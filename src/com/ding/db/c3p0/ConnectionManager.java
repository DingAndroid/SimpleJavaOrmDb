package com.ding.db.c3p0;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;

import com.ding.db.constants.Constant;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mchange.v2.c3p0.DataSources;

/**
 * c3p0 ���ݿ����ӳ� ����ģʽ
 * @author ���¸�
 * 2016��4��10��11:59:34
 */

public final class ConnectionManager {

	private static ConnectionManager instance;
	
	public ComboPooledDataSource ds;
	
	static { 
		//��ʼ��һЩ����
    }  

	private ConnectionManager() throws Exception {

		ds = new ComboPooledDataSource();
//		
//		ds.setDriverClass("oracle.jdbc.driver.OracleDriver");
//		ds.setJdbcUrl("jdbc:oracle:thin:@127.0.0.1:1521:orcl");
//		ds.setUser("test");
//		ds.setPassword("testtest");
		ds.setDriverClass(Constant.DB_DRVIER_CLASS);
		ds.setJdbcUrl(Constant.DB_URL);
		ds.setUser(Constant.DB_USER_NAME);
		ds.setPassword(Constant.DB_USER_PASSWORD);
		
		//��ʼ��ʱ��ȡ�������ӣ�ȡֵӦ��minPoolSize��maxPoolSize֮�䡣Default: 3 initialPoolSize
		ds.setInitialPoolSize(5);
		//���ӳ��б����������������Default: 15 maxPoolSize
		ds.setMaxPoolSize(6);
		//// ���ӳ��б�������С��������
		//ds.setMinPoolSize(1);
		//�����ӳ��е����Ӻľ���ʱ��c3p0һ��ͬʱ��ȡ����������Default: 3 acquireIncrement
		ds.setAcquireIncrement(3);
		
		//ÿ60�����������ӳ��еĿ������ӡ�Default: 0 idleConnectionTestPeriod
		ds.setIdleConnectionTestPeriod(60);
		//������ʱ��,25000����δʹ�������ӱ���������Ϊ0������������Default: 0 maxIdleTime
		ds.setMaxIdleTime(0);
		//���ӹر�ʱĬ�Ͻ�����δ�ύ�Ĳ����ع���Default: false autoCommitOnClose
		ds.setAutoCommitOnClose(true);
		
		//�����������Ӳ��Զ�ִ�еĲ�����䡣��ʹ�����Ӳ��Ե���������һ������߲����ٶȡ�ע�⣺
		//���Եı�����ڳ�ʼ����Դ��ʱ��ʹ��ڡ�Default: null preferredTestQuery
		ds.setPreferredTestQuery("select time from logs");
		// ���������Ĵ���ֻ����Ҫ��ʱ��ʹ�����������Ϊtrue��ô��ÿ��connection�ύ��
		// ʱ�򶼽�У������Ч�ԡ�����ʹ��idleConnectionTestPeriod��automaticTestTable
		// �ȷ������������Ӳ��Ե����ܡ�Default: false testConnectionOnCheckout
		ds.setTestConnectionOnCheckout(true);
		//�����Ϊtrue��ô��ȡ�����ӵ�ͬʱ��У�����ӵ���Ч�ԡ�Default: false testConnectionOnCheckin
		ds.setTestConnectionOnCheckin(true);
		
		//�����ڴ����ݿ��ȡ������ʧ�ܺ��ظ����ԵĴ�����Default: 30 acquireRetryAttempts
		ds.setAcquireRetryAttempts(30);
		//���������м��ʱ�䣬��λ���롣Default: 1000 acquireRetryDelay
		ds.setAcquireRetryDelay(1000);
		//��ȡ����ʧ�ܽ����������еȴ����ӳ�����ȡ���ӵ��߳��׳��쳣����������Դ����Ч
		//�����������´ε���getConnection()��ʱ��������Ի�ȡ���ӡ������Ϊtrue����ô�ڳ���
		//��ȡ����ʧ�ܺ������Դ�������ѶϿ������ùرա�Default: false breakAfterAcquireFailure
		ds.setBreakAfterAcquireFailure(true);

}

	public static final ConnectionManager getInstance() {
		if (instance == null) {
			try {
				instance = new ConnectionManager();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return instance;
	}

	public synchronized final Connection getConnection() {
		try {
			return ds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	protected void finalize() throws Throwable {
		DataSources.destroy(ds); // �ر�datasource
		super.finalize();
	}

	
	// <!--�����ӳ�����ʱ�ͻ��˵���getConnection()��ȴ���ȡ�����ӵ�ʱ�䣬��ʱ���׳�
	// SQLException,����Ϊ0�������ڵȴ�����λ���롣Default: 0 -->
	// <property name="checkoutTimeout">100</property>

	// <!--c3p0����һ����ΪTest�Ŀձ���ʹ�����Դ��Ĳ�ѯ�����в��ԡ�������������������ô
	// ����preferredTestQuery�������ԡ��㲻��������Test���Ͻ����κβ���������ֻ��c3p0����
	// ʹ�á�Default: null-->
	// <property name="automaticTestTable">Test</property>

	// <!--JDBC�ı�׼���������Կ�������Դ�ڼ��ص�PreparedStatements������������Ԥ�����statements
	// ���ڵ���connection�������������ӳء������������������Ҫ���ǵ��෽������ء�
	// ���maxStatements��maxStatementsPerConnection��Ϊ0���򻺴汻�رա�Default: 0-->
	// <property name="maxStatements">100</property>

	// <!--maxStatementsPerConnection���������ӳ��ڵ���������ӵ�е���󻺴�statements����Default: 0 -->
	// <property name="maxStatementsPerConnection"></property>

	// <!--c3p0���첽�����ģ�������JDBC����ͨ������������ɡ���չ��Щ����������Ч����������
	// ͨ�����߳�ʵ�ֶ������ͬʱ��ִ�С�Default: 3-->
	// <property name="numHelperThreads">3</property>

	// <!--�û��޸�ϵͳ���ò���ִ��ǰ���ȴ�300�롣Default: 300 -->
	// <property name="propertyCycle">300</property>
}