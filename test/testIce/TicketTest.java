package testIce;

import junit.framework.Assert;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import bean.TBOrder;
import service.ticket.TicketOrderServiceSpringImpl;

@RunWith(Junit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:anno.xml")
@FixMethodOrder(MethodSorters.NAME_ASCENDING) 
public class TicketTest {
	
	@Autowired
	private TicketOrderServiceSpringImpl ticketOrderServiceSpringImpl;
	
	@Test
	public void test_1_(){
		TBOrder order = new TBOrder();
		order.setId(1);
		order.setPhone("139001");
		order.setAmount(1);
		order.setOrderDate(20171012);
		order.setOrderStatus(0);
		order.setTicketType(100);
		order.setOrderNum("20151012001");
		
		Assert.assertTrue(ticketOrderServiceSpringImpl.createOrder(order));
		
		Assert.assertEquals(false, ticketOrderServiceSpringImpl.getOrders("139001").isEmpty());
		
		Assert.assertEquals(true, ticketOrderServiceSpringImpl.cancelOrder(1));
	}
	
}
