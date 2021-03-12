package com.yedam.exam;

import javax.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:config/*-context.xml")
public class SlipDaoClient {
	
	@Resource SlipDAO dao;
	
	@Test
	public void test() {
		Slip slip = new Slip();
		slip.setSlipAmount(200);
		slip.setSlipNo(20200708);
		slip.setCustomer("201_james");
		slip.setBankAcct("12-124-12");
		int r = dao.insertSlip(slip);
		System.out.println(r+"건 처리됨");
    }
}
