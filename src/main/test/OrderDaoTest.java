import com.wulei.DAO.OrderDao;
import com.wulei.Entity.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-dao.xml")
public class OrderDaoTest {
    @Autowired
    OrderDao orderDao;

    @Test
    public void getOrderListByDoctorIdTest(){
        List<Order> orderList = orderDao.getOrderListByDoctorId(1);
        Iterator iterator = orderList.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

    @Test
    public void insertOrderTest(){
        System.out.println(orderDao.insertOrder(
                new Order(0, 1, 1, "1", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()))
        ));
    }
}
