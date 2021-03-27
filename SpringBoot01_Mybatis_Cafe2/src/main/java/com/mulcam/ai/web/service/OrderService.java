package com.mulcam.ai.web.service;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mulcam.ai.web.dao.OrderDAOImpl;
import com.mulcam.ai.web.vo.OrderVO;

@Service
public class OrderService {
	@Autowired
	OrderDAOImpl orderDAO;
	
	ServerSocket ss;
	
	public OrderService() {
		try {
			ss = new ServerSocket(9999);
			new Thread(()-> {
					while(true) {
						try {
							Socket s = ss.accept();
							ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
							ObjectInputStream in = new ObjectInputStream(s.getInputStream());
							new KitchenThread(s, in, out).start();			
						} catch (IOException e) {
							e.printStackTrace();
						}			
					}
			}).start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public long insert(ArrayList<OrderVO> list) {
		long order_group_no=orderDAO.ordersInsert(list);
		// 주방에 주문 통보
		
		return order_group_no; 
	}
	
	private class KitchenThread extends Thread{
		Socket s;
		ObjectInputStream in;
		ObjectOutputStream out;
		
		public KitchenThread(Socket s, ObjectInputStream in, ObjectOutputStream out) {
			this.s = s;
			this.in = in;
			this.out = out;
		}
		
		@Override
		public void run() {
			try {
				while(true) {
					in.readObject();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
	}
} // end OrderService
