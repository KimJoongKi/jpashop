package yaddoong.jpashop.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import yaddoong.jpashop.domain.Order;
import yaddoong.jpashop.domain.OrderItem;
import yaddoong.jpashop.repository.OrderRepository;
import yaddoong.jpashop.repository.OrderSearch;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderApiController {

    private final OrderRepository orderRepository;

    @GetMapping("/api/v1/orders")
    public List<Order> ordersV1() {

        List<Order> allByString = orderRepository.findAllByString(new OrderSearch());
        for (Order order : allByString) {
            order.getMember().getName();
            order.getDelivery().getAddress();

            List<OrderItem> orderItems = order.getOrderItems();
            orderItems.stream().forEach(o -> o.getItem().getName());
        }
        return allByString;
    }
}
