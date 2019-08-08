package com.jluzh.jixun.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jluzh.jixun.domain.ConfirmOrderInfo;
import com.jluzh.jixun.domain.OrderInfo;
import com.jluzh.jixun.result.Result;
import com.jluzh.jixun.service.OrderService;
import com.jluzh.model.Order;


@Controller
@RequestMapping("/order")
public class OrderController {
	@Autowired
	OrderService orderService;
	
	@RequestMapping(value = "/generate",method = RequestMethod.POST)
	@ResponseBody
	public Result<ConfirmOrderInfo> generateConfirmOrder(){
		ConfirmOrderInfo res = orderService.CreateConfirmOrder();
		return Result.success(res);
	}
	
	@RequestMapping(value = "/create",method = RequestMethod.POST)
	@ResponseBody
	public Result createOrder(@RequestBody OrderInfo orderInfo){
		return orderService.CreateOrder(orderInfo);
	}
	
	@RequestMapping("/paycomplete")
	public Result<Integer> paycomplete(Long orderId){
		//TODO 待做
		return null;
	}
	
	@RequestMapping(value = "/cancelTimeOut",method = RequestMethod.POST)
    @ResponseBody
    public Result<String> cancelOrder(){
		Result<String> res = orderService.CancalDelayOrder();
        return res;
    }
	@RequestMapping(value = "/cancel",method = RequestMethod.POST)
    @ResponseBody
    public Result<String>  cancelOrder(Long orderId){
		orderService.cancelOrder(orderId);
		return Result.success("操作成功");
	}
	
	@RequestMapping(value="/getlist",method=RequestMethod.GET)
	@ResponseBody
	public Object getOrderList(@RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum){
		List<Order> list = orderService.getRencentOrder(pageSize, pageNum);
		return Result.pageSuccess(list);	
	}

}
