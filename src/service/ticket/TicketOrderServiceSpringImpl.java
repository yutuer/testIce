package service.ticket;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import service.onlinebook.OnlineBookImpl;
import bean.TBOrder;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@Component
public class TicketOrderServiceSpringImpl {

	private Logger logger = LoggerFactory.getLogger(OnlineBookImpl.class);

	private Map<String, List<TBOrder>> orderPhoneMap = Maps.newHashMap();
	private Map<Long, TBOrder> orderIdMap = Maps.newHashMap();

	public boolean createOrder(TBOrder tbOrder) {
		logger.info("create order:" + tbOrder);
		orderIdMap.put(tbOrder.getId(), tbOrder);

		List<TBOrder> list = orderPhoneMap.get(tbOrder.getPhone());
		if (list == null) {
			list = Lists.newArrayList();
			orderPhoneMap.put(tbOrder.getPhone(), list);
		}
		return list.add(tbOrder);
	}

	public List<TBOrder> getOrders(String phoneNum) {
		return orderPhoneMap.get(phoneNum);
	}

	public boolean cancelOrder(long orderId) {
		TBOrder order = orderIdMap.get(orderId);
		if(order != null){
			if (order.getOrderStatus() == 0 || order.getOrderStatus() == 1) {
				order.setOrderStatus(-1);
				orderIdMap.remove(orderId);
				
				List<TBOrder> list = orderPhoneMap.get(order.getPhone());
				for(Iterator<TBOrder> iter = list.iterator();iter.hasNext();){
					TBOrder tbOrder = iter.next();
					if(tbOrder.getId() == orderId){
						iter.remove();
					}
				}
				return true;
			}
		}
		return false;
	}
}
